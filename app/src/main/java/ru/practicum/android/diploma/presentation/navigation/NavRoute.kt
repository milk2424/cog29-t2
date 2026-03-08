package ru.practicum.android.diploma.presentation.navigation

import kotlinx.serialization.Serializable

@Serializable
data object Main

@Serializable
data object Favorites

@Serializable
data object Team

@Serializable
data class VacancyDetails(val id: String)

@Serializable
data object Filter

@Serializable
data class RegionSelection(val countryId: String?)
data object WorkplaceSelection

@Serializable
data object IndustrySelection
