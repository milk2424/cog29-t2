package ru.practicum.android.diploma.domain.utils

sealed interface ApiResult<out T> {
    data class Success<T>(val data: T) : ApiResult<T>
    data object ServerError : ApiResult<Nothing>
    data object NetworkError : ApiResult<Nothing>
    data object UnknownError : ApiResult<Nothing>
    data object Loading : ApiResult<Nothing>
}
