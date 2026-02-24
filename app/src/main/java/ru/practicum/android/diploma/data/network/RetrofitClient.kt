package ru.practicum.android.diploma.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.practicum.android.diploma.data.dto.AreasRequest
import ru.practicum.android.diploma.data.dto.AreasResponse
import ru.practicum.android.diploma.data.dto.IndustriesRequest
import ru.practicum.android.diploma.data.dto.IndustriesResponse
import ru.practicum.android.diploma.data.dto.Response
import ru.practicum.android.diploma.data.dto.VacanciesByFilterRequest
import ru.practicum.android.diploma.data.dto.VacancyDetailRequest
import ru.practicum.android.diploma.data.dto.VacancyDetailResponse
import ru.practicum.android.diploma.domain.NetworkChecker

class RetrofitClient(private val apiService: ApiService, private val networkChecker: NetworkChecker) : NetworkClient {
    override suspend fun doRequest(dto: Any): Response {
        if (!networkChecker.isNetworkAvailable())
            return Response().apply { resultCode = -1 }
        return withContext(Dispatchers.IO) {
            try {
                when (dto) {
                    is AreasRequest -> AreasResponse(apiService.getAreas()).ok()
                    is IndustriesRequest -> IndustriesResponse(apiService.getIndustries()).ok()
                    is VacanciesByFilterRequest -> apiService.getVacancies(
                        area = dto.area,
                        industry = dto.industry,
                        text = dto.text,
                        salary = dto.salary,
                        page = dto.page,
                        onlyWithSalary = dto.onlyWithSalary
                    ).ok()

                    is VacancyDetailRequest -> VacancyDetailResponse(
                        apiService.getVacancyById(dto.id)
                    ).ok()

                    else -> Response().apply { resultCode = 400 }
                }
            } catch (_: Throwable) {
                Response().apply { resultCode = 500 }
            }
        }
    }

    private fun Response.ok() = apply { resultCode = 200 }
}
