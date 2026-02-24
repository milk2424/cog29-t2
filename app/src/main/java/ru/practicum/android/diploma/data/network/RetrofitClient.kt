package ru.practicum.android.diploma.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.dto.Response
import ru.practicum.android.diploma.data.dto.areas.AreasRequest
import ru.practicum.android.diploma.data.dto.areas.AreasResponse
import ru.practicum.android.diploma.data.dto.industries.IndustriesRequest
import ru.practicum.android.diploma.data.dto.industries.IndustriesResponse
import ru.practicum.android.diploma.data.dto.vacancies.VacanciesByFilterRequest
import ru.practicum.android.diploma.data.dto.vacancydetail.VacancyDetailRequest
import ru.practicum.android.diploma.data.dto.vacancydetail.VacancyDetailResponse
import ru.practicum.android.diploma.domain.NetworkChecker

class RetrofitClient(private val apiService: ApiService, private val networkChecker: NetworkChecker) : NetworkClient {
    override suspend fun doRequest(dto: Any): Response {
        if (!networkChecker.isNetworkAvailable()) {
            return Response().apply { resultCode = NO_INTERNET }
        }
        return withContext(Dispatchers.IO) {
            try {
                when (dto) {
                    is AreasRequest -> AreasResponse(apiService.getAreas()).ok()
                    is IndustriesRequest -> IndustriesResponse(apiService.getIndustries()).ok()
                    is VacanciesByFilterRequest -> apiService.getVacancies(dto.toQueryMap()).ok()
                    is VacancyDetailRequest -> VacancyDetailResponse(apiService.getVacancyById(dto.id)).ok()
                    else -> Response().apply { resultCode = HTTP_BAD_REQUEST }
                }
            } catch (_: Throwable) {
                Response().apply { resultCode = HTTP_SERVER_ERROR }
            }
        }
    }

    private fun Response.ok() = apply { resultCode = HTTP_OK }

    private companion object {
        const val NO_INTERNET = -1
        const val HTTP_OK = 200
        const val HTTP_BAD_REQUEST = 400
        const val HTTP_SERVER_ERROR = 500
    }
}
