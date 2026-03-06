package ru.practicum.android.diploma.domain.model

data class Vacancy(
    val id: String,
    val name: String,
    val employer: VacancyEmployer,
    val areaName: String,
    val salary: VacancySalary?,
    val description: String,
    val experience: String?,
    val schedule: String?,
    val employment: String?,
    val skills: List<String>,
    val url: String,
    val contacts: VacancyContacts?,
)
