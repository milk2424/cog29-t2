package ru.practicum.android.diploma.domain.interactor

import ru.practicum.android.diploma.domain.model.FilterSettings
import ru.practicum.android.diploma.domain.repository.FilterRepository

class FilterInteractorImpl(private val repository: FilterRepository) : FilterInteractor {
    override fun saveFilter(settings: FilterSettings) {
        repository.saveFilter(settings)
    }

    override fun getFilter(): FilterSettings? {
        return repository.getFilter()
    }

    override fun clearFilter() {
        repository.clearFilter()
    }

    override fun hasActiveFilter(): Boolean {
        val filter = repository.getFilter() ?: return false
        return filter.salary != null ||
            filter.hideWithoutSalary ||
            filter.industryId != null ||
            filter.countryId != null ||
            filter.regionId != null
    }
}
