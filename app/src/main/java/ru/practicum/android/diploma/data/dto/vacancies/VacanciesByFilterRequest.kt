package ru.practicum.android.diploma.data.dto.vacancies

data class VacanciesByFilterRequest(
    val area: Int? = null,
    val industry: Int? = null,
    val text: String? = null,
    val salary: Int? = null,
    val onlyWithSalary: Boolean? = null,
    val page: Int? = null
)
