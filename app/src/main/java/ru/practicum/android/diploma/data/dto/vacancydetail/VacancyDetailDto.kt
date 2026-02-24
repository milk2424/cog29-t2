package ru.practicum.android.diploma.data.dto.vacancydetail

import ru.practicum.android.diploma.data.dto.areas.FilterAreaDto
import ru.practicum.android.diploma.data.dto.industries.FilterIndustryDto
import ru.practicum.android.diploma.data.model.Address
import ru.practicum.android.diploma.data.model.Contacts
import ru.practicum.android.diploma.data.model.Employer
import ru.practicum.android.diploma.data.model.Employment
import ru.practicum.android.diploma.data.model.Experience
import ru.practicum.android.diploma.data.model.Salary
import ru.practicum.android.diploma.data.model.Schedule

data class VacancyDetailDto(
    val id: String,
    val name: String,
    val description: String,
    val salary: Salary?,
    val address: Address?,
    val experience: Experience?,
    val schedule: Schedule?,
    val employment: Employment?,
    val contacts: Contacts?,
    val employer: Employer,
    val area: FilterAreaDto,
    val skills: List<String>,
    val url: String,
    val industry: FilterIndustryDto
)
