package ru.practicum.android.diploma.data.dto.vacancies

import ru.practicum.android.diploma.data.dto.Response
import ru.practicum.android.diploma.data.dto.vacancydetail.VacancyDetailDto

class VacanciesResponse(
    val found: Int = 0,
    val pages: Int = 0,
    val page: Int = 0,
    val vacancies: List<VacancyDetailDto> = emptyList()
) : Response()
