package ru.practicum.android.diploma.domain.interactor

import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.repository.FavoritesRepository

class FavoritesInteractorImpl(private val repository: FavoritesRepository) : FavoritesInteractor {
    override suspend fun toggleFavorite(vacancy: Vacancy) {
        if (repository.isFavorite(vacancy.id)) {
            repository.delete(vacancy.id)
        } else {
            repository.add(vacancy)
        }
    }
    override suspend fun add(vacancy: Vacancy) = repository.add(vacancy)
    override suspend fun remove(vacancyId: String) = repository.delete(vacancyId)
    override suspend fun isFavorite(vacancyId: String) = repository.isFavorite(vacancyId)
}
