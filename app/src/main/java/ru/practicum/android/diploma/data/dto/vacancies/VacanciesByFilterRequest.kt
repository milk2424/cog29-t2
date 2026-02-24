package ru.practicum.android.diploma.data.dto.vacancies

import ru.practicum.android.diploma.data.dto.AppRequest

data class VacanciesByFilterRequest(
    val area: Int? = null,
    val industry: Int? = null,
    val text: String? = null,
    val salary: Int? = null,
    val onlyWithSalary: Boolean? = null,
    val page: Int? = null
) : AppRequest

fun VacanciesByFilterRequest.toQueryMap(): Map<String, String> = buildMap {
    area?.let { put("area", it.toString()) }
    industry?.let { put("industry", it.toString()) }
    text?.let { put("text", it) }
    salary?.let { put("salary", it.toString()) }
    onlyWithSalary?.let { put("only_with_salary", it.toString()) }
    page?.let { put("page", it.toString()) }
}
