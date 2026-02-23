package ru.practicum.android.diploma.data.dto

class VacanciesResponse(
    val found: Int = 0,
    val pages: Int = 0,
    val page: Int = 0,
    val vacancies: List<VacancyDetailDto> = emptyList()
) : Response()
