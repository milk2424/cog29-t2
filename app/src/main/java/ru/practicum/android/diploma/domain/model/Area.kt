package ru.practicum.android.diploma.domain.model

import java.io.Serializable

data class Area(
    val id: String,
    val parentId: String?,
    val name: String,
    val areas: List<Area>
) : Serializable {
    companion object {
        private const val serialVersionUID = 1L
    }
}
