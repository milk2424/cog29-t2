package ru.practicum.android.diploma.domain.repository

import ru.practicum.android.diploma.domain.model.FilterSettings

interface FilterRepository {
    fun saveFilter(settings: FilterSettings)
    fun getFilter(): FilterSettings?
    fun clearFilter()
}
