package ru.practicum.android.diploma.presentation.filter.workplace

import androidx.compose.runtime.Immutable

@Immutable
data class WorkplaceSelectionScreenState(
    val countryName: String? = null,
    val countryId: String? = null,
    val regionName: String? = null,
    val regionId: String? = null
)
