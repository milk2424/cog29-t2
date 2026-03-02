package ru.practicum.android.diploma.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.Developer
import ru.practicum.android.diploma.domain.repository.TeamRepository

class LoadDevelopersTeamUseCase(private val repository: TeamRepository) {
    operator fun invoke(): Flow<List<Developer>> = repository.getTeamDevelopers()
}
