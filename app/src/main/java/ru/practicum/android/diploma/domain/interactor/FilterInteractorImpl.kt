package ru.practicum.android.diploma.domain.interactor

import ru.practicum.android.diploma.domain.model.FilterSettings
import ru.practicum.android.diploma.domain.repository.FilterRepository

class FilterInteractorImpl(private val repository: FilterRepository) : FilterInteractor {
    override fun saveFilter(settings: FilterSettings) {
        repository.saveFilter(settings)
    }

    override fun getFilter(): FilterSettings {
        return repository.getFilter()
    }
}
