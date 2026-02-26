package ru.practicum.android.diploma.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.practicum.android.diploma.util.debounce

class SearchViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val searchDebounce: (String) -> Unit = debounce<String>(
        delayMillis = SEARCH_DELAY,
        coroutineScope = viewModelScope
    ) { query ->
        performSearch(query)
    }

    fun onQueryChanged(query: String) {
        _uiState.value = _uiState.value.copy(
            query = query,
            isInitial = query.isBlank(),
            isLoading = false
        )

        if (query.isNotBlank()) {
            searchDebounce(query)
        }
    }

    fun onClearClicked() {
        _uiState.value = _uiState.value.copy(
            query = "",
            isInitial = true,
            isLoading = false
        )
    }

    fun onFilterClicked() {
        /**
         * implement filter here
         */
    }

    private fun performSearch(query: String) {
        _uiState.value = _uiState.value.copy(isLoading = true)
        /**
         * implement search here
         */
    }

    companion object {
        private const val SEARCH_DELAY = 2000L
    }

}
