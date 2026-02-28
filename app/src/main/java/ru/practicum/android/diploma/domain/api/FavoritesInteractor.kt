package ru.practicum.android.diploma.domain.api

import ru.practicum.android.diploma.domain.models.Vacancy

interface FavoritesInteractor {
    suspend fun toggleFavorite(vacancy: Vacancy)
    suspend fun add(vacancy: Vacancy)
    suspend fun remove(vacancyId: String)
    suspend fun isFavorite(vacancyId: String): Boolean
}
