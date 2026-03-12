package ru.practicum.android.diploma.domain.model

data class FilterSettings(
    val salary: Int?,
    val hideWithoutSalary: Boolean,
    val industryId: Int?,
    val industryName: String?,
    val countryId: Int?,
    val countryName: String?,
    val regionId: Int?,
    val regionName: String?
)
