package ru.practicum.android.diploma.data.repositoryimpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.data.database.VacancyDao
import ru.practicum.android.diploma.data.mapper.toDomain
import ru.practicum.android.diploma.data.mapper.toEntity
import ru.practicum.android.diploma.domain.api.FavoritesRepository
import ru.practicum.android.diploma.domain.models.Vacancy

class FavoritesRepositoryImpl(private val dao: VacancyDao) : FavoritesRepository {
    override fun getAll(): Flow<List<Vacancy>> = dao.getAllVacancies().map { list -> list.map { it.toDomain() } }
    override suspend fun add(vacancy: Vacancy) = dao.addVacancy(vacancy.toEntity())
    override suspend fun delete(vacancyId: String) = dao.deleteVacancy(vacancyId)
    override suspend fun isFavorite(vacancyId: String): Boolean = dao.getVacancy(vacancyId) != null
}
