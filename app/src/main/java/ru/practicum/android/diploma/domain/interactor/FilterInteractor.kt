package ru.practicum.android.diploma.domain.interactor

import ru.practicum.android.diploma.domain.model.FilterSettings

interface FilterInteractor {
    fun saveFilter(settings: FilterSettings)
    fun getFilter(): FilterSettings
}
