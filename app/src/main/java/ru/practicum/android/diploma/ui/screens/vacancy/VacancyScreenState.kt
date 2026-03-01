package ru.practicum.android.diploma.ui.screens.vacancy

import ru.practicum.android.diploma.domain.models.Vacancy

sealed interface VacancyScreenState {
    data object Loading : VacancyScreenState
    data object ServerError : VacancyScreenState
    data object NotFound : VacancyScreenState
    data class Content(
        val vacancy: Vacancy,
        val isFavorite: Boolean
    ) : VacancyScreenState
}