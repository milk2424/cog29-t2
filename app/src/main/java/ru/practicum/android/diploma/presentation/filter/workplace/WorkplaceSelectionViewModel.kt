package ru.practicum.android.diploma.presentation.filter.workplace

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class WorkplaceSelectionViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(WorkplaceSelectionScreenState())
    val uiState: StateFlow<WorkplaceSelectionScreenState> = _uiState.asStateFlow()

    fun onCountryCleared() {
        _uiState.update { it.copy(countryId = null, countryName = null, regionId = null, regionName = null) }
    }

    fun onRegionCleared() {
        _uiState.update { it.copy(regionId = null, regionName = null) }
    }

    fun onConfirmSelection() {
        // Передача назад в фильтр
    }
}
