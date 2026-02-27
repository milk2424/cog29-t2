package ru.practicum.android.diploma.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.SearchInteractor
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.util.Resource
import ru.practicum.android.diploma.util.debounce

class SearchViewModel(
    private val searchInteractor: SearchInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val searchDebounce: (String) -> Unit = debounce<String>(
        delayMillis = SEARCH_DELAY,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { query ->
        performSearch(query)
    }

    fun onQueryChanged(query: String) {
        _uiState.update {
            it.copy(
                query = query,
                isLoading = false,
                isInitial = query.isBlank()
            )
        }

        if (query.isNotBlank()) {
            searchDebounce(query)
        }
    }

    fun onClearClicked() {
        _uiState.update {
            SearchUiState()
        }
    }

    fun onFilterClicked() {
        /**
         * implement filter here
         */
    }

    private fun performSearch(query: String) {
        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            searchInteractor
                .searchVacancies(query, page = 0)
                .collect { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    vacancies = resource.data?.vacancies ?: emptyList()
                                )
                            }
                        }
                        // Будущая обработка ошибок
                        is Resource.Error -> {
                            _uiState.update { it.copy(isLoading = false) }
                        }
                    }
                }
        }
    }

    companion object {
        private const val SEARCH_DELAY = 2000L
    }
}

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val isInitial: Boolean = true,
    val vacancies: List<Vacancy> = emptyList()
)
