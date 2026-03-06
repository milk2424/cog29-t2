package ru.practicum.android.diploma.domain.model

data class FilterSettings(
    val salary: Int?,
    val hideWithoutSalary: Boolean,
    val industryId: String?,
    val industryName: String?,
    val countryId: String?,
    val countryName: String?,
    val regionId: String?,
    val regionName: String?
)
