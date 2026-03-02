package ru.practicum.android.diploma.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.repository.FavoritesRepository

class GetAllFavoritesUseCase(private val repository: FavoritesRepository) {
    operator fun invoke(): Flow<List<Vacancy>> = repository.getAll()
}
