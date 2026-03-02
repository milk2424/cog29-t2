package ru.practicum.android.diploma.domain.model

data class VacanciesResult(
    val vacancies: List<Vacancy>,
    val found: Int,
    val page: Int,
    val pages: Int
)
