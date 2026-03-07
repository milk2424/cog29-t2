package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.core.network.ApiService
import ru.practicum.android.diploma.core.network.NetworkCaller
import ru.practicum.android.diploma.data.mapper.toDomain
import ru.practicum.android.diploma.domain.model.Industry
import ru.practicum.android.diploma.domain.repository.IndustryRepository
import ru.practicum.android.diploma.domain.utils.ApiResult

class IndustryRepositoryImpl(
    private val api: ApiService,
    private val networkCaller: NetworkCaller
) : IndustryRepository {

    override fun getIndustries(): Flow<ApiResult<List<Industry>>> = flow {
        emit(ApiResult.Loading)
        val response = networkCaller.safeApiCall(
            apiCall = { api.getIndustries() },
            transform = { dtoList -> dtoList.toDomain() }
        )
        emit(response)
    }
}
