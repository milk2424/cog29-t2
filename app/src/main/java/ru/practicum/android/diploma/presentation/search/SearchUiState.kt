package ru.practicum.android.diploma.presentation.search

import androidx.annotation.StringRes
import androidx.compose.runtime.Immutable
import ru.practicum.android.diploma.domain.model.Vacancy

@Immutable
data class SearchUiState(
    val query: String = "",
    val isInitial: Boolean = true,
    val isLoading: Boolean = false,
    val isLoadingNextPage: Boolean = false,
    val isDebouncing: Boolean = false,
    val isError: Boolean = false,
    @get:StringRes val errorMessage: Int? = null,
    val vacancies: List<Vacancy> = emptyList(),
    val foundVacancies: Int = 0
)
