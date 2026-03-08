package ru.practicum.android.diploma.domain.model

fun FilterSettings.hasActiveFilter(): Boolean {
    return salary != null ||
        hideWithoutSalary ||
        (industryId != null && industryId != -1) ||
        (countryId != null && countryId != -1) ||
        (regionId != null && regionId != -1)
}
