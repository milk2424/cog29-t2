package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.core.database.VacancyEntity
import ru.practicum.android.diploma.data.dto.vacancydetail.VacancyDetailDto
import ru.practicum.android.diploma.data.model.Contacts
import ru.practicum.android.diploma.data.model.Employer
import ru.practicum.android.diploma.data.model.Employment
import ru.practicum.android.diploma.data.model.Experience
import ru.practicum.android.diploma.data.model.Phone
import ru.practicum.android.diploma.data.model.Salary
import ru.practicum.android.diploma.data.model.Schedule
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.model.VacancyContacts
import ru.practicum.android.diploma.domain.model.VacancyEmployer
import ru.practicum.android.diploma.domain.model.VacancySalary

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
            currency = it.currency,
            formattedCurrency = VacancySalary.from(it.currency)
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
            phone = it.phones.map { phone -> phone.formatted }
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
            currency = it.currency,
            formattedCurrency = VacancySalary.from(it.currency)
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
            phone = it.phones.map { p -> p.formatted }
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
            phones = it.phone?.map { p -> Phone(comment = null, formatted = p) } ?: emptyList()
        )
    },
    employer = Employer(id = "", name = employer.name, logo = employer.logo),
    areaName = areaName,
    skills = skills,
    industryName = null
)
