package ru.practicum.android.diploma.data.team.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.team.model.Developer
import ru.practicum.android.diploma.domain.team.repository.TeamRepository

class TeamRepositoryImpl : TeamRepository {
    override fun getTeamDevelopers(): Flow<List<Developer>> = flow {
        val developers = listOf(
            Developer(R.string.dev_vinokurov_name, R.string.dev_vinokurov_github),
            Developer(R.string.dev_milko_name, R.string.dev_milko_github),
            Developer(R.string.dev_pchelintsev_name, R.string.dev_pchelintsev_github),
            Developer(R.string.dev_salnikov_name, R.string.dev_salnikov_github),
            Developer(R.string.dev_sergeev_name, R.string.dev_sergeev_github)
        )
        emit(developers)
    }
}
