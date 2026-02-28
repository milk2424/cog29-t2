package ru.practicum.android.diploma.ui.screens.vacancy

import ru.practicum.android.diploma.domain.models.Vacancy

sealed interface VacancyScreenState {
    object Loading : VacancyScreenState
    data class Error(val type: ErrorType) : VacancyScreenState
    data class Content(
        val vacancy: Vacancy,
        val isFavorite: Boolean
    ) : VacancyScreenState
    enum class ErrorType { SERVER_ERROR, NOT_FOUND }
}
