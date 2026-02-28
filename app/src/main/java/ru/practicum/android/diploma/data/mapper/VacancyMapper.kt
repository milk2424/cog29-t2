package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.database.entity.VacancyEntity
import ru.practicum.android.diploma.data.dto.vacancydetail.VacancyDetailDto
import ru.practicum.android.diploma.data.model.Contacts
import ru.practicum.android.diploma.data.model.Employer
import ru.practicum.android.diploma.data.model.Employment
import ru.practicum.android.diploma.data.model.Experience
import ru.practicum.android.diploma.data.model.Salary
import ru.practicum.android.diploma.data.model.Schedule
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

fun VacancyEntity.toDomain() = Vacancy(
    id = id,
    name = name,
    employer = VacancyEmployer(
        name = employer.name,
        logo = employer.logo,
    ),
    areaName = areaName,
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

fun Vacancy.toEntity() = VacancyEntity(
    id = id,
    name = name,
    description = description,
    url = url,
    salary = salary?.let {
        Salary(
            from = it.from,
            to = it.to,
            currency = it.currency
        )
    },
    address = null,
    experience = experience?.let { Experience(id = "", name = it) },
    schedule = schedule?.let { Schedule(id = "", name = it) },
    employment = employment?.let { Employment(id = "", name = it) },
    contacts = contacts?.let {
        Contacts(
            id = "",
            name = it.name ?: "",
            email = it.email ?: "",
            phone = it.phone ?: emptyList()
        )
    },
    employer = Employer(id = "", name = employer.name, logo = employer.logo),
    areaName = areaName,
    skills = skills,
    industryName = null
)
