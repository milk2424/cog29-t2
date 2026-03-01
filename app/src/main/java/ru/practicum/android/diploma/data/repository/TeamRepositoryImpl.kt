package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.domain.model.Developer
import ru.practicum.android.diploma.domain.repository.TeamRepository

class TeamRepositoryImpl(private val developers: List<Developer>) : TeamRepository {
    override fun getTeamDevelopers(): Flow<List<Developer>> = flow {
        emit(developers)
    }
}
