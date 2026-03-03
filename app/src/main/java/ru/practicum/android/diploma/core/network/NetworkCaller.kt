package ru.practicum.android.diploma.core.network

import android.util.Log
import retrofit2.Response
import ru.practicum.android.diploma.domain.utils.ApiResult
import java.io.IOException

class NetworkCaller(private val networkChecker: NetworkChecker) {
    companion object {
        private const val HTTP_NOT_FOUND = 404
    }

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

                response.code() == HTTP_NOT_FOUND -> {
                    ApiResult.NotFoundError
                }

                else -> {
                    ApiResult.ServerError(code = response.code())
                }
            }
        } catch (e: IOException) {
            Log.e("NetworkCaller", "Network error", e)
            ApiResult.NetworkError
        } catch (e: Exception) {
            Log.e("NetworkCaller", "Unknown error", e)
            ApiResult.UnknownError
        }
    }
}
