package ru.practicum.android.diploma.domain.model

fun FilterSettings.hasActiveFilter(): Boolean {
    return salary != null ||
        hideWithoutSalary ||
        industryId != null ||
        countryId != null ||
        regionId != null
}
