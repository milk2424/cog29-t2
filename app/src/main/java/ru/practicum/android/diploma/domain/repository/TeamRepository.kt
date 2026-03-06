package ru.practicum.android.diploma.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.Developer

interface TeamRepository {

    fun getTeamDevelopers(): Flow<List<Developer>>

}
