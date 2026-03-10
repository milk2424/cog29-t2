package ru.practicum.android.diploma.domain.interactor

import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.repository.FavoritesRepository

class FavoritesInteractorImpl(private val repository: FavoritesRepository) : FavoritesInteractor {

    override suspend fun add(vacancy: Vacancy) = repository.add(vacancy)
    override suspend fun remove(vacancyId: String) = repository.delete(vacancyId)
    override suspend fun isFavorite(vacancyId: String) = repository.isFavorite(vacancyId)
    override suspend fun getById(vacancyId: String): Vacancy? = repository.getById(vacancyId)
}
