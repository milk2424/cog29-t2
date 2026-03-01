package ru.practicum.android.diploma.data.dto.areas

data class FilterAreaDto(
    val id: String,
    val name: String,
    val parentId: String?,
    val areas: List<FilterAreaDto>
)
