package ru.practicum.android.diploma.presentation.vacancy

import androidx.compose.runtime.Immutable
import ru.practicum.android.diploma.domain.model.Vacancy

@Immutable
sealed interface VacancyScreenState {
    data object Loading : VacancyScreenState
    data object ServerError : VacancyScreenState
    data object NotFound : VacancyScreenState
    data class Content(
        val vacancy: Vacancy,
        val isFavorite: Boolean
    ) : VacancyScreenState
}
