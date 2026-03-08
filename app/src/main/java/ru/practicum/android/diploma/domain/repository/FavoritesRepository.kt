package ru.practicum.android.diploma.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.Vacancy

interface FavoritesRepository {
    fun getAll(): Flow<List<Vacancy>>
    suspend fun add(vacancy: Vacancy)
    suspend fun delete(vacancyId: String)
    suspend fun isFavorite(vacancyId: String): Boolean
    suspend fun getById(vacancyId: String): Vacancy?
}
