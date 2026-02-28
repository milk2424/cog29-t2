package ru.practicum.android.diploma.data.repositoryimpl

import android.content.Context
import android.content.Intent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.diploma.data.dto.vacancies.VacanciesByFilterRequest
import ru.practicum.android.diploma.data.dto.vacancies.toDomain
import ru.practicum.android.diploma.data.dto.vacancies.toQueryMap
import ru.practicum.android.diploma.data.mapper.toDomain
import ru.practicum.android.diploma.data.network.ApiService
import ru.practicum.android.diploma.data.network.utils.NetworkCaller
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.domain.api.utils.ApiResult
import ru.practicum.android.diploma.domain.models.VacanciesResult
import ru.practicum.android.diploma.domain.models.Vacancy

class VacancyRepositoryImpl(
    private val api: ApiService,
    private val networkCaller: NetworkCaller,
    private val context: Context
) : VacancyRepository {
    override fun searchVacancies(expression: String, page: Int): Flow<ApiResult<VacanciesResult>> = flow {
        emit(ApiResult.Loading)
        val request = VacanciesByFilterRequest(text = expression, page = page)
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

    override fun shareVacancy(message: String) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(shareIntent)
    }
}
