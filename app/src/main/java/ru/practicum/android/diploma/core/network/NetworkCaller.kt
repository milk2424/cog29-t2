package ru.practicum.android.diploma.core.network

import retrofit2.Response
import ru.practicum.android.diploma.domain.utils.ApiResult

class NetworkCaller(private val networkChecker: NetworkChecker) {
    suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> Response<T>,
        transform: (T) -> R
    ): ApiResult<R> {
        if (!networkChecker.isNetworkAvailable()) return ApiResult.NetworkError
        return try {
            val response = apiCall()
            when {
                response.isSuccessful -> {
                    val resultData = response.body()
                    if (resultData == null) {
                        ApiResult.ServerError(code = response.code())
                    } else {
                        val mappedData = transform(resultData)
                        ApiResult.Success(mappedData)
                    }
                }

                response.code() == 404 -> {
                    ApiResult.NotFoundError
                }

                else -> {
                    ApiResult.ServerError(code = response.code())
                }
            }
        } catch (e: Exception) {
            ApiResult.UnknownError
        }
    }
}
