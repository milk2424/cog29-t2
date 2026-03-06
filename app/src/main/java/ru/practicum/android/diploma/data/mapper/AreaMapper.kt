package ru.practicum.android.diploma.data.mapper

import ru.practicum.android.diploma.data.dto.areas.FilterAreaDto
import ru.practicum.android.diploma.domain.model.Area

fun FilterAreaDto.toDomain(): Area {
    return Area(
        id = id,
        parentId = parentId,
        name = name,
        areas = areas.map { it.toDomain() }
    )
}

fun List<FilterAreaDto>.toDomain(): List<Area> {
    return this.map { it.toDomain() }
}
