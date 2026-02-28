package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.Vacancy

interface FavoritesRepository {
    fun getAll(): Flow<List<Vacancy>>
    suspend fun add(vacancy: Vacancy)
    suspend fun delete(vacancyId: String)
    suspend fun isFavorite(vacancyId: String): Boolean
}
