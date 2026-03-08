package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.industries.FilterIndustryDto
import ru.practicum.android.diploma.domain.model.Industry

fun FilterIndustryDto.toDomain(): Industry {
    return Industry(
        id = this.id,
        name = this.name
    )
}

fun List<FilterIndustryDto>.toDomain(): List<Industry> {
    return this.map { it.toDomain() }
}
