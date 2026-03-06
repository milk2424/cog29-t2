package ru.practicum.android.diploma.core.storage

import ru.practicum.android.diploma.domain.model.FilterSettings

interface FilterStorage {
    fun saveFilter(filter: FilterSettings)
    fun getFilter(): FilterSettings?
    fun clearFilter()
}
