package ru.practicum.android.diploma.domain.interactor

import ru.practicum.android.diploma.domain.model.Vacancy

interface FavoritesInteractor {
    suspend fun toggleFavorite(vacancy: Vacancy)
    suspend fun add(vacancy: Vacancy)
    suspend fun remove(vacancyId: String)
    suspend fun isFavorite(vacancyId: String): Boolean
    suspend fun getById(vacancyId: String): Vacancy?
}
