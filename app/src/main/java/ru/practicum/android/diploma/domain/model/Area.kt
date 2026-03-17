package ru.practicum.android.diploma.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Area(
    val id: String,
    val parentId: String?,
    val name: String,
    val areas: List<Area>
)
