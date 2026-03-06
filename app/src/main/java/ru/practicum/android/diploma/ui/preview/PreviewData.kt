package ru.practicum.android.diploma.ui.preview

import ru.practicum.android.diploma.data.dto.areas.FilterAreaDto
import ru.practicum.android.diploma.data.dto.industries.FilterIndustryDto
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.model.VacancyContacts
import ru.practicum.android.diploma.domain.model.VacancyEmployer
import ru.practicum.android.diploma.domain.model.VacancySalary

@Suppress("StringLiteralDuplication", "MaxLineLength")
object PreviewData {
    val previewVacancy get() = previewVacancies[0]

    val previewAreas = listOf(
        FilterAreaDto(id = "1", name = "Москва", parentId = "113", areas = emptyList()),
        FilterAreaDto(id = "2", name = "Санкт-Петербург", parentId = "113", areas = emptyList()),
        FilterAreaDto(id = "1261", name = "Свердловская область", parentId = "113", areas = emptyList()),
        FilterAreaDto(id = "1438", name = "Краснодарский край", parentId = "113", areas = emptyList()),
        FilterAreaDto(id = "1202", name = "Новосибирская область", parentId = "113", areas = emptyList()),
    )
    val previewIndustries = listOf(
        FilterIndustryDto(id = "7", name = "Информационные технологии, системная интеграция, интернет"),
        FilterIndustryDto(id = "9", name = "Телекоммуникации, связь"),
        FilterIndustryDto(id = "43", name = "Финансовый сектор"),
        FilterIndustryDto(id = "48", name = "Медицина, фармацевтика, аптеки"),
        FilterIndustryDto(id = "41", name = "Розничная торговля"),
    )

    private const val DESCRIPTION_SAMPLE = "Задачи, которые могут стать твоими:\n\n" +
        "Разработка новой функциональности мобильного приложения под Android, его архитектуры и исправление существующих недостатков;\n" +
        "Написание качественного, чистого, читаемого кода, code-review;\n" +
        "Разработка общих архитектурных решений;\n\n" +
        "Что нужно знать:\n\n" +
        "Android SDK, Android Support Libraries\n" +
        "Kotlin\n" +
        "Dagger, Kotlin Coroutines, Kotlin Flow, Compose, MVVM / MVI, Room\n" +
        "Gradle Multi Modules\n\n" +
        "Будет плюсом:\n\n" +
        "Опыт RxJava\n" +
        "Опыт написания Unit и UI тестов\n" +
        "Опыт работы с CI&DI"

    val previewVacancies = listOf(
        Vacancy(
            id = "0006e2cd-695b-4778-8244-a6242d6dd790",
            name = "Android Developer в Microsoft",
            employer = VacancyEmployer(
                name = "Microsoft",
                logo = "https://upload.wikimedia.org/wikipedia/commons/thumb/4/44/Microsoft_logo.svg/1200px-Microsoft_logo.svg.png"
            ),
            areaName = "Чекрушево",
            salary = VacancySalary(from = 1500, to = 2500, currency = "EUR", formattedCurrency = VacancySalary.from("EUR")),
            description = DESCRIPTION_SAMPLE,
            experience = "Нет опыта",
            schedule = "Полный день",
            employment = "Полная занятость",
            skills = listOf("TypeScript", "Python", "Go", "Swift", "JavaScript"),
            url = "cek6h0n4ffe7ur.cluster-czz5s0kz4scl.eu-west-1.rds.amazonaws.com/vacancies/0006e2cd-695b-4778-8244-a6242d6dd790",
            contacts = VacancyContacts(
                name = "Попов Андрей Сергеевич",
                email = "123@gmail.com",
                phone = listOf("+7 (999) 678-90-12", "+7 (999) 432-10-98")
            ),
        ),
        Vacancy(
            id = "000d8a23-08b1-412d-8981-f659a3a2e1e4",
            name = "Android Developer в Яндекс",
            employer = VacancyEmployer(
                name = "Яндекс",
                logo = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Yandex_logo_2021_Russian.svg/1024px-Yandex_logo_2021_Russian.svg.png"
            ),
            areaName = "Новотулка (Самарская область)",
            salary = VacancySalary(from = null, to = 2200, currency = "GBP", formattedCurrency = VacancySalary.from("GBR")),
            description = DESCRIPTION_SAMPLE,
            experience = "Нет опыта",
            schedule = "Полный день",
            employment = "Полная занятость",
            skills = listOf("Ruby", "React", "Swift", "Kotlin", "Java"),
            url = "cek6h0n4ffe7ur.cluster-czz5s0kz4scl.eu-west-1.rds.amazonaws.com/vacancies/000d8a23-08b1-412d-8981-f659a3a2e1e4",
            contacts = VacancyContacts(
                name = "Попов Андрей Сергеевич",
                email = "123@gmail.com",
                phone = listOf("+7 (999) 678-90-12", "+7 (999) 432-10-98")
            ),
        ),
        Vacancy(
            id = "0011a7d9-a59a-4308-81f3-70269ac1e015",
            name = "Android Developer в Яндекс",
            employer = VacancyEmployer(
                name = "Яндекс",
                logo = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Yandex_logo_2021_Russian.svg/1024px-Yandex_logo_2021_Russian.svg.png"
            ),
            areaName = "Комсомольск (Ивановская область)",
            salary = VacancySalary(from = 8000, to = 18000, currency = "HKD", formattedCurrency = VacancySalary.from("HKD")),
            description = DESCRIPTION_SAMPLE,
            experience = "Нет опыта",
            schedule = "Полный день",
            employment = "Полная занятость",
            skills = listOf("NoSQL", "Java", "PHP", "SQL", "JavaScript"),
            url = "cek6h0n4ffe7ur.cluster-czz5s0kz4scl.eu-west-1.rds.amazonaws.com/vacancies/0011a7d9-a59a-4308-81f3-70269ac1e015",
            contacts = VacancyContacts(
                name = "Петров Петр Петрович",
                email = "",
                phone = listOf("+7 (999) 234-56-78", "+7 (999) 876-54-32")
            ),
        ),
        Vacancy(
            id = "00161f3f-0180-4289-b9f5-6d7468cdef2c",
            name = "Android Developer в Apple",
            employer = VacancyEmployer(
                name = "Apple",
                logo = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Apple_logo_black.svg/1200px-Apple_logo_black.svg.png"
            ),
            areaName = "Сосновый",
            salary = VacancySalary(from = 1500, to = 2500, currency = "EUR", formattedCurrency = VacancySalary.from("EUR")),
            description = DESCRIPTION_SAMPLE,
            experience = "Нет опыта",
            schedule = "Полный день",
            employment = "Полная занятость",
            skills = listOf("TypeScript", "PHP", "JavaScript", "Go", "React"),
            url = "cek6h0n4ffe7ur.cluster-czz5s0kz4scl.eu-west-1.rds.amazonaws.com/vacancies/00161f3f-0180-4289-b9f5-6d7468cdef2c",
            contacts = VacancyContacts(
                name = "Смирнов Алексей Иванович",
                email = "123@gmail.com",
                phone = listOf("+7 (999) 456-78-90", "+7 (999   ) 654-32-10")
            ),
        ),
        Vacancy(
            id = "00169da4-02ba-4713-ac84-0d917ca99f6c",
            name = "Android Developer в Яндекс",
            employer = VacancyEmployer(
                name = "Яндекс",
                logo = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Yandex_logo_2021_Russian.svg/1024px-Yandex_logo_2021_Russian.svg.png"
            ),
            areaName = "Судай",
            salary = VacancySalary(from = 1500, to = 2500, currency = "EUR", formattedCurrency = VacancySalary.from("EUR")),
            description = DESCRIPTION_SAMPLE,
            experience = "Нет опыта",
            schedule = "Полный день",
            employment = "Полная занятость",
            skills = listOf("Go", "SQL", "TypeScript", "React", "CSS"),
            url = "cek6h0n4ffe7ur.cluster-czz5s0kz4scl.eu-west-1.rds.amazonaws.com/vacancies/00169da4-02ba-4713-ac84-0d917ca99f6c",
            contacts = VacancyContacts(
                name = "Попов Андрей Сергеевич",
                email = "123@gmail.com",
                phone = listOf("+7 (999) 678-90-12", "+7 (999) 432-10-98")
            ),
        ),
    )
}
