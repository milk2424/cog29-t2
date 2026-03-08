package ru.practicum.android.diploma.presentation.filter.workplace

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.practicum.android.diploma.domain.model.Area

class WorkplaceSelectionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WorkplaceSelectionScreenState())
    val uiState: StateFlow<WorkplaceSelectionScreenState> = _uiState.asStateFlow()

    fun onCountryCleared() {
        _uiState.update { it.copy(countryId = null, countryName = null, regionId = null, regionName = null) }
    }

    fun onRegionCleared() {
        _uiState.update { it.copy(regionId = null, regionName = null) }
    }

    fun onConfirmSelection(navController: NavController) {
        val current = _uiState.value
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.set("country", current.countryId to current.countryName)
        navController.currentBackStackEntry
            ?.savedStateHandle
            ?.set("region", current.regionId to current.regionName)
        navController.popBackStack()
    }

    fun onCountrySelected(area: Area) {
        _uiState.update { it.copy(countryId = area.id, countryName = area.name, regionId = null, regionName = null) }

    }
}
