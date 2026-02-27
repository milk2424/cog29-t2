package ru.practicum.android.diploma.data.network.utils

import retrofit2.Response
import ru.practicum.android.diploma.domain.api.utils.ApiResult
import ru.practicum.android.diploma.domain.api.utils.NetworkChecker

class NetworkCaller(private val networkChecker: NetworkChecker) {
    suspend fun <T, R> safeApiCall(apiCall: suspend () -> Response<T>, transform: (T) -> R): ApiResult<R> {
        if (!networkChecker.isNetworkAvailable()) return ApiResult.NetworkError

        return try {
            val response = apiCall()
            if (response.isSuccessful) {
                val resultData = response.body()
                if (resultData == null) {
                    ApiResult.ServerError
                } else {
                    val mappedData = transform(resultData)
                    ApiResult.Success(mappedData)
                }
            } else {
                ApiResult.ServerError
            }
        } catch (_: Exception) {
            ApiResult.UnknownError
        }
    }
}
