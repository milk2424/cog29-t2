package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.diploma.core.network.ApiService
import ru.practicum.android.diploma.core.network.NetworkCaller
import ru.practicum.android.diploma.data.dto.vacancies.VacanciesByFilterRequest
import ru.practicum.android.diploma.data.dto.vacancies.toDomain
import ru.practicum.android.diploma.data.dto.vacancies.toQueryMap
import ru.practicum.android.diploma.data.mapper.toDomain
import ru.practicum.android.diploma.domain.model.VacanciesResult
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.repository.VacancyRepository
import ru.practicum.android.diploma.domain.utils.ApiResult

class VacancyRepositoryImpl(private val api: ApiService, private val networkCaller: NetworkCaller) :
    VacancyRepository {
    override fun searchVacancies(
        expression: String,
        page: Int,
        salary: Int?,
        onlyWithSalary: Boolean?,
        industry: Int?,
        area: Int?
    ): Flow<ApiResult<VacanciesResult>> = flow {
        emit(ApiResult.Loading)
        val request = VacanciesByFilterRequest(
            text = expression,
            page = page,
            salary = salary,
            onlyWithSalary = onlyWithSalary,
            industry = industry,
            area = area
        )
        val response = networkCaller.safeApiCall(
            apiCall = { api.getVacancies(request.toQueryMap()) },
            transform = { dto -> dto.toDomain() }
        )
        emit(response)
    }.flowOn(Dispatchers.IO)

    override fun getVacancyDetail(id: String): Flow<ApiResult<Vacancy>> = flow {
        emit(ApiResult.Loading)
        val response = networkCaller.safeApiCall(
            apiCall = { api.getVacancyById(id) },
            transform = { dto -> dto.toDomain() }
        )
        emit(response)
    }.flowOn(Dispatchers.IO)
}
