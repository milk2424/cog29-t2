package ru.practicum.android.diploma.presentation.filter

import androidx.lifecycle.ViewModel
import ru.practicum.android.diploma.domain.interactor.FilterInteractor
import ru.practicum.android.diploma.domain.model.FilterSettings

class FilterViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {
    fun saveFilter(settings: FilterSettings) {
        filterInteractor.saveFilter(settings)
    }

    fun loadFilter(): FilterSettings? {
        return filterInteractor.getFilter()
    }

    fun clearFilter() {
        filterInteractor.clearFilter()
    }
}
