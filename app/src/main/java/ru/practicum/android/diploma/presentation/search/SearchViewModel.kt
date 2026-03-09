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
import ru.practicum.android.diploma.domain.model.FilterSettings
import ru.practicum.android.diploma.domain.model.VacanciesResult
import ru.practicum.android.diploma.domain.model.hasActiveFilter
import ru.practicum.android.diploma.domain.utils.ApiResult

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val filterInteractor: FilterInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var currentPage = 0
    private var maxPages = 0
    private var isNextPageLoading = false
    private var currentQuery = ""
    var lastAppliedFilter: FilterSettings? = null

    private val _filter = MutableStateFlow(filterInteractor.getFilter())
    val filter = _filter.asStateFlow()

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

        isNextPageLoading = true
        _uiState.update {
            it.copy(isLoadingNextPage = true)
        }

        viewModelScope.launch {
            searchInteractor
                .searchVacancies(currentQuery, page = currentPage + 1)
                .collectLatest { result ->
                    handlePaginationResult(result)
                }
        }
    }

    fun refreshSearch(filter: FilterSettings) {
        lastAppliedFilter = filter
        _filter.value = filter
        if (currentQuery.isNotBlank()) {
            performNewSearch(currentQuery)
        }
    }

    fun updateFilter(newFilter: FilterSettings) {
        _filter.value = newFilter
    }

    private fun shouldLoadNextPage(state: SearchUiState): Boolean {
        return !isNextPageLoading &&
            !state.isLoading &&
            !state.isLoadingNextPage &&
            currentQuery.isNotBlank() &&
            currentPage < maxPages - 1 &&
            state.vacancies.isNotEmpty()
    }

    private fun performNewSearch(query: String) {
        currentPage = 0
        maxPages = 0
        currentQuery = query
        isNextPageLoading = false

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
                .searchVacancies(query, page = 0)
                .collectLatest { result ->
                    handleSearchResult(result)
                }
        }
    }

    private fun handleSearchResult(result: ApiResult<VacanciesResult>) {
        when (result) {
            is ApiResult.Loading -> showLoading()

            is ApiResult.Success -> showSuccess(result.data)

            is ApiResult.NetworkError -> showError(R.string.error_no_internet)

            else -> showError(R.string.error_occurred)
        }
    }

    private fun handlePaginationResult(result: ApiResult<VacanciesResult>) {
        when (result) {
            is ApiResult.Loading -> {
                _uiState.update {
                    it.copy(isLoadingNextPage = true)
                }
            }

            is ApiResult.Success -> {
                result.data.let { data ->
                    currentPage = data.page
                    maxPages = data.pages

                    val currentList = _uiState.value.vacancies.toMutableList()
                    currentList.addAll(data.vacancies)

                    _uiState.update {
                        it.copy(
                            isLoadingNextPage = false,
                            vacancies = currentList,
                            isError = false,
                            errorMessage = null
                        )
                    }
                }
                isNextPageLoading = false
            }

            is ApiResult.NetworkError -> {
                showPaginationError(R.string.error_no_internet)
            }

            else -> {
                showPaginationError(R.string.error_occurred)
            }
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

    private fun showSuccess(data: VacanciesResult?) {
        data ?: return
        currentPage = data.page
        maxPages = data.pages

        _uiState.update {
            it.copy(
                isLoading = false,
                isDebouncing = false,
                vacancies = data.vacancies,
                foundVacancies = data.found,
                isError = false,
                errorMessage = null
            )
        }
    }

    private fun showPaginationError(messageRes: Int) {
        isNextPageLoading = false
        _uiState.update {
            it.copy(
                isLoadingNextPage = false,
                errorMessage = messageRes
            )
        }
    }

    private fun clearSearchResults() {
        currentPage = 0
        maxPages = 0
        currentQuery = ""
        isNextPageLoading = false

        _uiState.update {
            SearchUiState()
        }
    }

    companion object {
        private const val SEARCH_DELAY = 2000L
    }
}
