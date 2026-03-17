package ru.practicum.android.diploma.presentation.filter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.interactor.FilterInteractor
import ru.practicum.android.diploma.domain.model.FilterSettings

class FilterSharedViewModel(private val filterInteractor: FilterInteractor) : ViewModel() {
    private val _filter = MutableStateFlow(
        FilterSettings(
            salary = null,
            hideWithoutSalary = false,
            industryId = null,
            industryName = null,
            countryId = null,
            countryName = null,
            regionId = null,
            regionName = null
        )
    )
    val filter: StateFlow<FilterSettings> = _filter

    private val _applyFilter = MutableSharedFlow<Unit>()
    val applyFilter = _applyFilter.asSharedFlow()

    init {
        viewModelScope.launch {
            val savedFilter = filterInteractor.getFilter()
            _filter.value = savedFilter.copy(
                countryId = savedFilter.countryId?.takeIf { it > 0 },
                regionId = savedFilter.regionId?.takeIf { it > 0 }
            )
        }
    }

    fun applyFilter() {
        viewModelScope.launch {
            _applyFilter.emit(Unit)
        }
    }

    fun setCountry(
        countryId: Int?,
        countryName: String?
    ) {
        val id = countryId?.takeIf { it > 0 }
        _filter.update {
            it.copy(
                countryId = id,
                countryName = countryName,
                regionId = null,
                regionName = null
            )
        }
        saveFilter()
    }

    fun setRegion(
        regionId: Int?,
        regionName: String?
    ) {
        val id = regionId?.takeIf { it > 0 }
        _filter.update {
            it.copy(
                regionId = id,
                regionName = regionName
            )
        }
        saveFilter()
    }

    fun setIndustry(
        industryId: Int?,
        industryName: String?
    ) {
        val id = industryId?.takeIf { it > 0 }
        _filter.update {
            it.copy(
                industryId = id,
                industryName = industryName
            )
        }
        saveFilter()
    }

    fun setSalary(
        salary: Int?
    ) {
        _filter.update {
            it.copy(
                salary = salary
            )
        }
        saveFilter()
    }

    fun setHideWithoutSalary(
        hideWithoutSalary: Boolean
    ) {
        _filter.update {
            it.copy(
                hideWithoutSalary = hideWithoutSalary
            )
        }
        saveFilter()
    }

    fun resetFilter() {
        _filter.value = FilterSettings(
            salary = null,
            hideWithoutSalary = false,
            industryId = null,
            industryName = null,
            countryId = null,
            countryName = null,
            regionId = null,
            regionName = null
        )
        saveFilter()
    }

    fun saveFilter() {
        val currentFilter = _filter.value
        filterInteractor.saveFilter(currentFilter)

    }
}
