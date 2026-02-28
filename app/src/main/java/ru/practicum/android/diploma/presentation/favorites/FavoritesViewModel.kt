package ru.practicum.android.diploma.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.practicum.android.diploma.domain.favorites.usecase.GetAllFavoritesUseCase
import ru.practicum.android.diploma.presentation.favorites.FavoritesScreenState.Empty
import ru.practicum.android.diploma.presentation.favorites.FavoritesScreenState.Error
import ru.practicum.android.diploma.presentation.favorites.FavoritesScreenState.Loading
import ru.practicum.android.diploma.presentation.favorites.FavoritesScreenState.Success

class FavoritesViewModel(getFavouritesUseCase: GetAllFavoritesUseCase) : ViewModel() {

    val screenState: StateFlow<FavoritesScreenState> = getFavouritesUseCase()
        .map { vacancies ->
            if (vacancies.isEmpty()) Empty
            else Success(vacancies.toPersistentList())
        }
        .catch { emit(Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = Loading
        )
}
