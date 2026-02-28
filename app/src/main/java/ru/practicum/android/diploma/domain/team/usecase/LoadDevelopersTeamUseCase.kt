package ru.practicum.android.diploma.domain.team.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.team.model.Developer
import ru.practicum.android.diploma.domain.team.repository.TeamRepository

class LoadDevelopersTeamUseCase(private val repository: TeamRepository) {
    operator fun invoke(): Flow<List<Developer>> = repository.getTeamDevelopers()
}
