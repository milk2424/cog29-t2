package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.network.ApiService
import ru.practicum.android.diploma.core.network.NetworkCaller
import ru.practicum.android.diploma.data.mapper.toDomain
import ru.practicum.android.diploma.domain.model.Area
import ru.practicum.android.diploma.domain.repository.CountryRepository
import ru.practicum.android.diploma.domain.utils.ApiResult

class CountryRepositoryImpl(
    private val api: ApiService,
    private val networkCaller: NetworkCaller
) : CountryRepository {
    override fun loadCountries(): Flow<ApiResult<List<Area>>> = flow {
        emit(ApiResult.Loading)
        val response = networkCaller.executeApiCall(
            apiCall = { api.getAreas() },
            transform = { dto -> dto.toDomain() }
        )
        emit(response)
    }

    override suspend fun getCountryById(countryId: String): Area? {
        val result = loadCountries().first { it !is ApiResult.Loading }
        return when (result) {
            is ApiResult.Success -> {
                result.data
                    .filter { it.parentId == null }
                    .find { it.id == countryId }
            }

            else -> null
        }
    }
}
