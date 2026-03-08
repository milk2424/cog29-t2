package ru.practicum.android.diploma.data.dto.vacancies

import android.util.Log
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
    area?.takeIf { it > 0 }?.let { put("area", it.toString()) }
    Log.d(".....................", "Area in query map: $area")
    industry?.takeIf { it > 0 }?.let { put("industry", it.toString()) }
    Log.d(".....................", "Indus in query map: $industry")
    text?.takeIf { it.isNotBlank() }?.let { put("text", it) }
    salary?.let { put("salary", it.toString()) }
    onlyWithSalary?.let { put("only_with_salary", it.toString()) }
    page?.let { put("page", it.toString()) }
}
