package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.vacancydetail.VacancyDetailDto
import ru.practicum.android.diploma.domain.models.Vacancy

fun VacancyDetailDto.toDomain() = Vacancy(
    id = id,
    name = name,
    employerName = employer.name,
    employerLogo = employer.logo,
    areaName = area.name,
    salaryFrom = salary?.from,
    salaryTo = salary?.to,
    salaryCurrency = salary?.currency,
    description = description,
    experience = experience?.name,
    schedule = schedule?.name,
    employment = employment?.name,
    skills = skills,
    url = url,
    contactName = contacts?.name,
    contactEmail = contacts?.email,
    contactPhone = contacts?.phone
)
