package ru.practicum.android.diploma.data.repository

import ru.practicum.android.diploma.core.storage.FilterStorage
import ru.practicum.android.diploma.domain.model.FilterSettings
import ru.practicum.android.diploma.domain.repository.FilterRepository
import kotlin.takeIf

class FilterRepositoryImpl(
    private val filterStorage: FilterStorage

) : FilterRepository {
    override fun saveFilter(settings: FilterSettings) {
        filterStorage.saveFilter(settings)
    }

    override fun getFilter(): FilterSettings {
        val saved = filterStorage.getFilter()
        return saved.copy(
            countryId = saved.countryId?.takeIf { it > 0 },
            regionId = saved.regionId?.takeIf { it > 0 },
            industryId = saved.industryId?.takeIf { it > 0 }

        )
    }

    override fun clearFilter() {
        filterStorage.clearFilter()
    }
}
