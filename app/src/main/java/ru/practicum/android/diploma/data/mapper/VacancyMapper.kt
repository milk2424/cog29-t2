package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.vacancydetail.VacancyDetailDto
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyContacts
import ru.practicum.android.diploma.domain.models.VacancyEmployer
import ru.practicum.android.diploma.domain.models.VacancySalary

fun VacancyDetailDto.toDomain() = Vacancy(
    id = id,
    name = name,
    employer = VacancyEmployer(
        name = employer.name,
        logo = employer.logo,
    ),
    areaName = area.name,
    salary = salary?.let {
        VacancySalary(
            from = it.from,
            to = it.to,
            currency = it.currency
        )
    },
    description = description,
    experience = experience?.name,
    schedule = schedule?.name,
    employment = employment?.name,
    skills = skills,
    url = url,
    contacts = contacts?.let {
        VacancyContacts(
            name = it.name,
            email = it.email,
            phone = it.phone
        )
    }
)
