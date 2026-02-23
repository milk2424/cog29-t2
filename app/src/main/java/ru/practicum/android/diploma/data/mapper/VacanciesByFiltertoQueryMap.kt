package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.VacanciesByFilterRequest

fun VacanciesByFilterRequest.toQueryMap(): Map<String, Any> {
    val map = mutableMapOf<String, Any>()

    area?.let { map["area"] = it }
    industry?.let { map["industry"] = it }
    text?.let { map["text"] = it }
    salary?.let { map["salary"] = it }
    onlyWithSalary?.let { map["only_with_salary"] = it }
    page?.let { map["page"] = it }

    return map
}
