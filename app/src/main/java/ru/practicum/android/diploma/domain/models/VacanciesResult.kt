package ru.practicum.android.diploma.domain.models

data class VacanciesResult(
    val vacancies: List<Vacancy>,
    val found: Int
)
