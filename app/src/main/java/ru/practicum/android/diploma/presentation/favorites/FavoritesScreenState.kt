package ru.practicum.android.diploma.presentation.favorites

import kotlinx.collections.immutable.PersistentList
import ru.practicum.android.diploma.domain.model.Vacancy

sealed interface FavoritesScreenState {
    data object Loading : FavoritesScreenState
    data object Error : FavoritesScreenState
    data object Empty : FavoritesScreenState
    data class Success(val vacancies: PersistentList<Vacancy>) : FavoritesScreenState
}
