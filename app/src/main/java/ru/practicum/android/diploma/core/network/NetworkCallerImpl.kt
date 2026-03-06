package ru.practicum.android.diploma.core.network

import android.util.Log
import com.google.gson.JsonParseException
import retrofit2.Response
import ru.practicum.android.diploma.domain.utils.ApiResult

class NetworkCallerImpl(private val networkChecker: NetworkChecker) : NetworkCaller {
    override suspend fun <T, R> safeApiCall(
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
        } catch (e: JsonParseException) {
            Log.e("NetworkCaller", "JSON parsing error", e)
            ApiResult.UnknownError
        }
    }

    companion object {
        private const val HTTP_NOT_FOUND = 404
    }
}
