package ru.practicum.android.diploma.domain.favorites.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.FavoritesRepository
import ru.practicum.android.diploma.domain.models.Vacancy

class GetAllFavoritesUseCase(private val repository: FavoritesRepository) {
    operator fun invoke(): Flow<List<Vacancy>> = repository.getAll()
}
