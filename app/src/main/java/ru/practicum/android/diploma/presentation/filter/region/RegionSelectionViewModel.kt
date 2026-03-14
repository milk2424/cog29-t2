package ru.practicum.android.diploma.presentation.filter.region

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.model.Area
import ru.practicum.android.diploma.domain.usecase.GetRegionsUseCase
import ru.practicum.android.diploma.domain.utils.ApiResult
import ru.practicum.android.diploma.domain.usecase.GetCountryByIdUseCase

class RegionSelectionViewModel(
    countryId: String?,
    private val getRegionsUseCase: GetRegionsUseCase,
    private val getCountryByIdUseCase: GetCountryByIdUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegionSelectionScreenState())
    val uiState: StateFlow<RegionSelectionScreenState> = _uiState.asStateFlow()

    init {
        loadRegions(countryId)
    }

    private fun loadRegions(countryId: String?) {
        viewModelScope.launch {
            getRegionsUseCase(countryId).collect { result ->
                when (result) {
                    is ApiResult.Success -> handleSuccess(result.data)
                    is ApiResult.Loading -> handleLoading()
                    else -> handleError()
                }
            }
        }
    }

    private fun handleSuccess(regions: List<Area>) {
        _uiState.update {
            it.copy(
                isLoading = false,
                isError = false,
                regions = regions,
                originalRegions = regions
            )
        }
    }

    private fun handleLoading() {
        _uiState.update {
            it.copy(
                isLoading = true,
                isError = false
            )
        }
    }

    private fun handleError() {
        _uiState.update {
            it.copy(
                isLoading = false,
                isError = true,
                regions = emptyList(),
                originalRegions = emptyList()
            )
        }
    }

    fun onQueryChanged(query: String) {
        _uiState.update {
            it.copy(query = query)
        }
        filter(query)
    }

    fun onClearClicked() {
        _uiState.update {
            it.copy(query = "")
        }
        _uiState.update { it.copy(regions = it.originalRegions) }
    }

    private fun filter(searchQuery: String) {
        if (searchQuery.isEmpty()) {
            _uiState.update { it.copy(regions = it.originalRegions) }
        } else {
            val filtered = _uiState.value.originalRegions.filter { item ->
                item.name.contains(searchQuery, ignoreCase = true)
            }
            _uiState.update { it.copy(regions = filtered) }
        }
    }

    fun getCountryName(countryId: String, onResult: (String?) -> Unit) {
        viewModelScope.launch {
            val country = getCountryByIdUseCase(countryId)
            onResult(country?.name)
        }
    }
}
