package ru.practicum.android.diploma.util

sealed class Resource<T>(
    val data: T? = null,
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(val errorType: ErrorType) : Resource<T>()
}

enum class ErrorType {
    NO_INTERNET,
    SERVER_ERROR
}
