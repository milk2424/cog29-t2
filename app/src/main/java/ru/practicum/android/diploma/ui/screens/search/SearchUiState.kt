package ru.practicum.android.diploma.ui.screens.search

data class SearchUiState(
    val query: String = "",
    val isLoading: Boolean = false,
    val isInitial: Boolean = true
    /**
     * val results: List<Vacancy> = emptyList()
     */
)
