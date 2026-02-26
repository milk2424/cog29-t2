package ru.practicum.android.diploma.data.team.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.domain.team.model.Developer
import ru.practicum.android.diploma.domain.team.repository.TeamRepository

class TeamRepositoryImpl(private val developers: List<Developer>) : TeamRepository {
    override fun getTeamDevelopers(): Flow<List<Developer>> = flow {
        emit(developers)
    }
}
