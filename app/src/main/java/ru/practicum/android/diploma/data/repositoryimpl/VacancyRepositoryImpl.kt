package ru.practicum.android.diploma.data.repositoryimpl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import ru.practicum.android.diploma.data.dto.vacancies.VacanciesByFilterRequest
import ru.practicum.android.diploma.data.dto.vacancies.VacanciesResponse
import ru.practicum.android.diploma.data.network.HttpCodes.HTTP_OK
import ru.practicum.android.diploma.data.network.HttpCodes.NO_INTERNET
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.domain.models.VacanciesResult
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.util.Resource

class VacancyRepositoryImpl(private val networkClient: NetworkClient) : VacancyRepository {
    override fun searchVacancies(expression: String, page: Int): Flow<Resource<VacanciesResult>> = flow {
        val response = networkClient.doRequest(VacanciesByFilterRequest(text = expression, page = page))
        when (response.resultCode) {
            NO_INTERNET -> {
                emit(Resource.Error("Проверьте подключение к интернету"))
            }

            HTTP_OK -> {
                with(response as VacanciesResponse) {
                    val data = vacancies.map {
                        Vacancy(
                            id = it.id,
                            name = it.name,
                            employerName = it.employer.name,
                            employerLogo = it.employer.logo,
                            areaName = it.area.name,
                            salaryFrom = it.salary?.from,
                            salaryTo = it.salary?.to,
                            salaryCurrency = it.salary?.currency,
                            description = it.description,
                            experience = it.experience?.name,
                            schedule = it.schedule?.name,
                            employment = it.employment?.name,
                            skills = it.skills,
                            url = it.url,
                            contactName = it.contacts?.name,
                            contactEmail = it.contacts?.email,
                            contactPhone = it.contacts?.phone
                        )
                    }
                    emit(Resource.Success(VacanciesResult(data, found, this.page, pages)))
                }
            }

            else -> {
                emit(Resource.Error("Ошибка сервера"))
            }
        }
    }
}
