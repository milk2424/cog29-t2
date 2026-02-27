package ru.practicum.android.diploma.ui.preview

import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancyContacts
import ru.practicum.android.diploma.domain.models.VacancyEmployer
import ru.practicum.android.diploma.domain.models.VacancySalary

object PreviewData {
    val previewVacancy = Vacancy(
        id = "1",
        name = "Android-разработчик",
        employer = VacancyEmployer(name = "Еда", logo = ""),
        areaName = "Москва",
        salary = VacancySalary(from = 100000, to = null, currency = "RUR"),
        description = "Описание вакансии",
        experience = "От 1 года до 3 лет",
        schedule = "Удаленная работа",
        employment = "Полная занятость",
        skills = listOf("Kotlin", "WebRTC"),
        url = "https://hh.ru/vacancy/1",
        contacts = VacancyContacts(
            name = "Ирина",
            email = "i.lozgkina@yandex.ru",
            phone = listOf("+7 (904) 329-27-71")
        ),
    )
}
