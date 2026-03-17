package ru.practicum.android.diploma.presentation.filter.region

import androidx.compose.runtime.Immutable
import ru.practicum.android.diploma.domain.model.Area

@Immutable
data class RegionSelectionScreenState(
    val query: String = "",
    val countryName: String? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val regions: List<Area> = emptyList(),
    val originalRegions: List<Area> = emptyList()
)
