package ru.practicum.android.diploma.presentation.filter.country

import kotlinx.collections.immutable.PersistentList
import ru.practicum.android.diploma.domain.model.Area

sealed interface CountrySelectionScreenState {
    data object Loading : CountrySelectionScreenState
    data object Error : CountrySelectionScreenState
    data class Success(val countries: PersistentList<Area>) : CountrySelectionScreenState
}
