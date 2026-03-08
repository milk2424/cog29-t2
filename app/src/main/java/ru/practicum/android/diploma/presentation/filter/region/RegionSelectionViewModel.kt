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

class RegionSelectionViewModel(
    countryId: String?,
    private val getRegionsUseCase: GetRegionsUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegionSelectionScreenState())
    val uiState: StateFlow<RegionSelectionScreenState> = _uiState.asStateFlow()

    private val originalList: MutableList<Area> = mutableListOf()
    private val filteredList: MutableList<Area> = mutableListOf()

    init {
        loadRegions(countryId)
    }

    fun loadRegions(countryId: String?) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, isError = false) }
            getRegionsUseCase(countryId)
                .collect { result ->
                    when (result) {
                        is ApiResult.Loading -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = true,
                                    isError = false
                                )
                            }
                        }

                        is ApiResult.Success -> {
                            result.data.let { regions ->
                                originalList.clear()
                                originalList.addAll(regions)
                                _uiState.update {
                                    it.copy(
                                        isLoading = false,
                                        isError = false,
                                        regions = regions,
                                        isEmpty = regions.isEmpty()
                                    )
                                }
                            }
                        }

                        else -> {
                            _uiState.update {
                                it.copy(
                                    isLoading = false,
                                    isError = true,
                                    regions = emptyList(),
                                    isEmpty = true
                                )
                            }
                        }
                    }
                }
        }
    }

    private fun updateDisplayList(updatedList: List<Area>) {
        _uiState.update {
            it.copy(regions = updatedList)
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
        updateDisplayList(originalList)
    }

    fun filter(searchQuery: String) {
        filteredList.clear()
        if (searchQuery.isEmpty()) {
            updateDisplayList(originalList)
        } else {
            for (item in originalList) {
                if (item.name.contains(searchQuery, ignoreCase = true)) {
                    filteredList.add(item)
                }
            }
            updateDisplayList(filteredList)
        }
    }
}
