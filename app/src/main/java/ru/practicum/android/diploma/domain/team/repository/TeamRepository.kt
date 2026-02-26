package ru.practicum.android.diploma.domain.team.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.team.model.Developer

interface TeamRepository {

    fun getTeamDevelopers(): Flow<List<Developer>>

}
