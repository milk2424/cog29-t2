package ru.practicum.android.diploma.presentation.search

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.api.SearchInteractor
import ru.practicum.android.diploma.domain.api.utils.ApiResult
import ru.practicum.android.diploma.domain.models.VacanciesResult
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.util.debounce

class SearchViewModel(
    private val searchInteractor: SearchInteractor,
    private val context: Context
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private var currentPage = 0
    private var maxPages = 0
    private var isNextPageLoading = false
    private var currentQuery = ""

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
        _uiState.update {
            SearchUiState()
        }
        clearSearchResults()
    }

    fun onFilterClicked() {
        /**
         * implement filter here
         */
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
                isLoading = true,
                isLoadingNextPage = false,
                isError = false,
                errorMessage = null,
                vacancies = emptyList()
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
            is ApiResult.Success -> {
                result.data?.let { data ->
                    currentPage = data.page
                    maxPages = data.pages

                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            vacancies = data.vacancies,
                            foundVacancies = data.found,
                            isError = false,
                            errorMessage = null
                        )
                    }
                }
            }
            is ApiResult.Loading -> {
            }
            is ApiResult.NetworkError -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = context.getString(R.string.error_no_internet)
                    )
                }
            }
            is ApiResult.ServerError, is ApiResult.UnknownError -> {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        isError = true,
                        errorMessage = context.getString(R.string.error_occurred)
                    )
                }
            }
        }
    }

    private fun handlePaginationResult(result: ApiResult<VacanciesResult>) {
        when (result) {
            is ApiResult.Success -> {
                result.data?.let { data ->
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
            is ApiResult.Loading -> {
            }
            is ApiResult.NetworkError -> {
                showPaginationError(context.getString(R.string.error_no_internet))
            }
            is ApiResult.ServerError, is ApiResult.UnknownError -> {
                showPaginationError(context.getString(R.string.error_occurred))
            }
        }
    }

    private fun showPaginationError(message: String) {
        isNextPageLoading = false
        _uiState.update {
            it.copy(
                isLoadingNextPage = false,
                errorMessage = message
            )
        }
    }

    private fun clearSearchResults() {
        currentPage = 0
        maxPages = 0
        currentQuery = ""
        isNextPageLoading = false
    }

    companion object {
        private const val SEARCH_DELAY = 2000L
    }
}

data class SearchUiState(
    val query: String = "",
    val isInitial: Boolean = true,
    val isLoading: Boolean = false,
    val isLoadingNextPage: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null,
    val vacancies: List<Vacancy> = emptyList(),
    val foundVacancies: Int = 0
)
