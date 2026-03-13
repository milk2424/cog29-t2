package ru.practicum.android.diploma.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.domain.interactor.FilterInteractor
import ru.practicum.android.diploma.domain.interactor.SearchInteractor
import ru.practicum.android.diploma.domain.model.VacanciesResult
import ru.practicum.android.diploma.domain.model.hasActiveFilter
import ru.practicum.android.diploma.domain.utils.ApiResult
import ru.practicum.android.diploma.presentation.filter.FilterSharedViewModel
import ru.practicum.android.diploma.presentation.search.components.PaginationManager

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    filterInteractor: FilterInteractor,
    private val sharedViewModel: FilterSharedViewModel
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val paginationManager = PaginationManager()

    private var currentQuery = ""

    private val _filter = MutableStateFlow(
        filterInteractor.getFilter()
    )

    init {
        viewModelScope.launch {
            sharedViewModel.filter.collectLatest {
                _filter.value = it
            }
        }
        viewModelScope.launch {
            sharedViewModel.applyFilter.collect {
                if (currentQuery.isNotBlank()) {
                    performNewSearch(currentQuery)
                }
            }
        }
    }

    val hasFilter: StateFlow<Boolean> = _filter
        .map { it.hasActiveFilter() }
        .stateIn(viewModelScope, SharingStarted.Lazily, _filter.value.hasActiveFilter())

    private val _events = MutableSharedFlow<SearchEvent>()
    val events: SharedFlow<SearchEvent> = _events

    sealed class SearchEvent {
        data class ShowError(val messageRes: Int) : SearchEvent()
    }

    private val searchDebounce: (String) -> Unit = debounce<String>(
        delayMillis = SEARCH_DELAY,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { query ->
        performNewSearch(query)
    }

    fun onQueryChanged(query: String) {
        _uiState.update {
            it.copy(
                query = query,
                isLoading = false,
                isInitial = query.isBlank(),
                isDebouncing = query.isNotBlank(),
                isError = false,
                errorMessage = null
            )
        }

        if (query.isNotBlank()) {
            searchDebounce(query)
        } else {
            clearSearchResults()
        }
    }

    fun onClearClicked() {
        clearSearchResults()
    }

    fun loadNextPage() {
        val currentState = _uiState.value

        if (!shouldLoadNextPage(currentState)) {
            return
        }

        paginationManager.isLoadingNextPage = true
        _uiState.update {
            it.copy(isLoadingNextPage = true)
        }

        viewModelScope.launch {
            searchInteractor
                .searchVacancies(currentQuery, page = paginationManager.currentPage + 1, filter = _filter.value)
                .collectLatest { result ->
                    handlePaginationResult(result)
                }
        }
    }

    private fun shouldLoadNextPage(state: SearchUiState): Boolean {
        return !state.isLoading &&
            !state.isLoadingNextPage &&
            currentQuery.isNotBlank() &&
            paginationManager.canLoadNextPage(state.vacancies.isNotEmpty())
    }

    private fun performNewSearch(query: String) {
        paginationManager.currentPage = 0
        paginationManager.maxPages = 0
        currentQuery = query
        paginationManager.isLoadingNextPage = false

        _uiState.update {
            it.copy(
                isLoadingNextPage = false,
                isDebouncing = false,
                isError = false,
                errorMessage = null,
                vacancies = emptyList(),
                isLoading = true
            )
        }

        viewModelScope.launch {
            searchInteractor
                .searchVacancies(query, page = 0, filter = _filter.value)
                .collectLatest { result ->
                    handleSearchResult(result)
                }
        }
    }

    private fun handleSearchResult(result: ApiResult<VacanciesResult>) {
        when (result) {
            is ApiResult.Loading -> showLoading()
            is ApiResult.Success -> updateVacancies(result.data)
            is ApiResult.NetworkError -> showError(R.string.error_no_internet)
            else -> showError(R.string.error_occurred)
        }
    }

    private fun handlePaginationResult(result: ApiResult<VacanciesResult>) {
        when (result) {
            is ApiResult.Loading -> _uiState.update { it.copy(isLoadingNextPage = true) }
            is ApiResult.Success -> updateVacancies(result.data, append = true)
            is ApiResult.NetworkError -> showPaginationError(R.string.error_no_internet)
            else -> showPaginationError(R.string.error_occurred)
        }
    }

    private fun showLoading() {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    private fun showError(messageRes: Int) {
        _uiState.update {
            it.copy(
                isLoading = false,
                isDebouncing = false,
                isError = true,
                errorMessage = messageRes
            )
        }
        viewModelScope.launch {
            _events.emit(SearchEvent.ShowError(messageRes))
        }
    }

    private fun showPaginationError(messageRes: Int) {
        paginationManager.isLoadingNextPage = false
        _uiState.update {
            it.copy(
                isLoadingNextPage = false,
                errorMessage = messageRes
            )
        }
    }

    private fun clearSearchResults() {
        paginationManager.reset()
        currentQuery = ""

        _uiState.update {
            SearchUiState()
        }
    }

    private fun updateVacancies(
        data: VacanciesResult,
        append: Boolean = false
    ) {
        val currentList = if (append) _uiState.value.vacancies.toMutableList() else mutableListOf()
        if (append) currentList.addAll(data.vacancies) else currentList.addAll(data.vacancies)

        _uiState.update {
            it.copy(
                isLoading = false,
                isLoadingNextPage = false,
                isDebouncing = false,
                vacancies = currentList,
                foundVacancies = data.found,
                isError = false,
                errorMessage = null
            )
        }
        paginationManager.currentPage = data.page
        paginationManager.maxPages = data.pages
        paginationManager.isLoadingNextPage = false
    }

    companion object {
        private const val SEARCH_DELAY = 2000L
    }
}
