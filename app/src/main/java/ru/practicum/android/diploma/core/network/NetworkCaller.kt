package ru.practicum.android.diploma.core.network

import retrofit2.Response
import ru.practicum.android.diploma.domain.utils.ApiResult

interface NetworkCaller {
    suspend fun <T, R> safeApiCall(
        apiCall: suspend () -> Response<T>,
        transform: (T) -> R
    ): ApiResult<R>
}
