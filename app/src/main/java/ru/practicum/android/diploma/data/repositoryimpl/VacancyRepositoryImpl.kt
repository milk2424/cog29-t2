package ru.practicum.android.diploma.data.repositoryimpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.dto.vacancies.VacanciesByFilterRequest
import ru.practicum.android.diploma.data.dto.vacancies.VacanciesResponse
import ru.practicum.android.diploma.data.mapper.toDomain
import ru.practicum.android.diploma.data.network.HttpCodes.HTTP_OK
import ru.practicum.android.diploma.data.network.HttpCodes.NO_INTERNET
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.domain.models.VacanciesResult
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.util.ErrorType
import ru.practicum.android.diploma.util.Resource

class VacancyRepositoryImpl(private val networkClient: NetworkClient) : VacancyRepository {
    override fun searchVacancies(expression: String, page: Int): Flow<Resource<VacanciesResult>> = flow {
        val response = networkClient.doRequest(VacanciesByFilterRequest(text = expression, page = page))
        when (response.resultCode) {
            NO_INTERNET -> { emit(Resource.Error(ErrorType.NO_INTERNET)) }
            HTTP_OK -> {
                with(response as VacanciesResponse) {
                    val data = vacancies.map { it.toDomain() }
                    emit(Resource.Success(VacanciesResult(data, found, this.page, pages)))
                }
            }
            else -> emit(Resource.Error(ErrorType.SERVER_ERROR))
        }
    }
}
