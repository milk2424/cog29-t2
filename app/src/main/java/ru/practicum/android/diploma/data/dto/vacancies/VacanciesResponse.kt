package ru.practicum.android.diploma.data.dto.vacancies

import com.google.gson.annotations.SerializedName
import ru.practicum.android.diploma.data.dto.vacancydetail.VacancyDetailDto
import ru.practicum.android.diploma.data.mapper.toDomain
import ru.practicum.android.diploma.domain.model.VacanciesResult

data class VacanciesResponse(
    val found: Int = 0,
    val pages: Int = 0,
    val page: Int = 0,
    @SerializedName("items")
    val vacancies: List<VacancyDetailDto> = emptyList()
)

fun VacanciesResponse.toDomain(): VacanciesResult {
    return VacanciesResult(
        vacancies = vacancies.map { it.toDomain() },
        found = found,
        page = page,
        pages = pages
    )
}
