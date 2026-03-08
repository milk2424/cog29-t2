package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.core.database.VacancyDao
import ru.practicum.android.diploma.data.mapper.toDomain
import ru.practicum.android.diploma.data.mapper.toEntity
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.repository.FavoritesRepository

class FavoritesRepositoryImpl(private val dao: VacancyDao) : FavoritesRepository {
    override fun getAll(): Flow<List<Vacancy>> = dao.getAllVacancies().map { list -> list.map { it.toDomain() } }
    override suspend fun add(vacancy: Vacancy) = dao.addVacancy(vacancy.toEntity())
    override suspend fun delete(vacancyId: String) = dao.deleteVacancy(vacancyId)
    override suspend fun isFavorite(vacancyId: String): Boolean = dao.getVacancy(vacancyId) != null
    override suspend fun getById(vacancyId: String): Vacancy? = dao.getVacancy(vacancyId)?.toDomain()
}
