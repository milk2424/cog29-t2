package ru.practicum.android.diploma.domain.impl

import ru.practicum.android.diploma.domain.api.FavoritesInteractor
import ru.practicum.android.diploma.domain.api.FavoritesRepository
import ru.practicum.android.diploma.domain.models.Vacancy

class FavoritesInteractorImpl(private val repository: FavoritesRepository) : FavoritesInteractor {
    override fun getAll() = repository.getAll()
    override suspend fun add(vacancy: Vacancy) = repository.add(vacancy)
    override suspend fun remove(vacancyId: String) = repository.delete(vacancyId)
    override suspend fun isFavorite(vacancyId: String) = repository.isFavorite(vacancyId)
}
