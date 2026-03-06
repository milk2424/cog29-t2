package ru.practicum.android.diploma.domain.utils

import ru.practicum.android.diploma.domain.model.Area

private const val OTHER_REGIONS_ID = "1001"

fun List<Area>.sortedWithOtherAtEnd(): List<Area> {
    val other = this.find { it.id == OTHER_REGIONS_ID }
    val base = this.filter { it.id != OTHER_REGIONS_ID }
    return if (other != null) base + other else base
}
