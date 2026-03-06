package ru.practicum.android.diploma.presentation.favorites

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList
import ru.practicum.android.diploma.domain.model.Vacancy

@Immutable
sealed interface FavoritesScreenState {
    data object Loading : FavoritesScreenState
    data object Error : FavoritesScreenState
    data object Empty : FavoritesScreenState
    data class Success(val vacancies: PersistentList<Vacancy>) : FavoritesScreenState
}
