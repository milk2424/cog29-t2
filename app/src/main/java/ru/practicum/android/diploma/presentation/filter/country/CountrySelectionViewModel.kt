package ru.practicum.android.diploma.presentation.filter.country

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.SharingStarted.Companion.Lazily
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.practicum.android.diploma.domain.usecase.GetAllCountriesUseCase
import ru.practicum.android.diploma.domain.utils.ApiResult
import ru.practicum.android.diploma.presentation.filter.country.CountrySelectionScreenState.Error
import ru.practicum.android.diploma.presentation.filter.country.CountrySelectionScreenState.Loading
import ru.practicum.android.diploma.presentation.filter.country.CountrySelectionScreenState.Success

class CountrySelectionViewModel(getCountriesUseCase: GetAllCountriesUseCase) : ViewModel() {

    val screenState: StateFlow<CountrySelectionScreenState> = getCountriesUseCase()
        .map { result ->
            when (result) {
                is ApiResult.Loading -> Loading
                is ApiResult.NetworkError,
                is ApiResult.NotFoundError,
                is ApiResult.ServerError,
                is ApiResult.UnknownError -> Error

                is ApiResult.Success -> Success(result.data.toPersistentList())
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = Lazily,
            initialValue = Loading
        )
}
