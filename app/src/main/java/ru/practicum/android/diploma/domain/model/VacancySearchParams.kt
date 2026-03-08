package ru.practicum.android.diploma.domain.model

data class VacancySearchParams(
    val expression: String,
    val page: Int,
    val salary: Int? = null,
    val onlyWithSalary: Boolean? = null,
    val industry: Int? = null,
    val area: Int? = null

)
