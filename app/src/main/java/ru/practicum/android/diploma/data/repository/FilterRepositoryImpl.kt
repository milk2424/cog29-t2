package ru.practicum.android.diploma.data.repository

import ru.practicum.android.diploma.core.storage.FilterStorage
import ru.practicum.android.diploma.domain.model.FilterSettings
import ru.practicum.android.diploma.domain.repository.FilterRepository

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage

) : FilterRepository {
    override fun saveFilter(settings: FilterSettings) {
        filterStorage.saveFilter(settings)
    }

    override fun getFilter(): FilterSettings? {
        return filterStorage.getFilter()
    }

    override fun clearFilter() {
        filterStorage.clearFilter()
    }
}
