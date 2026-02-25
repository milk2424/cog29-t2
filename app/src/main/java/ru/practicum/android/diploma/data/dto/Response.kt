package ru.practicum.android.diploma.data.dto

open class Response {
    var resultCode = -1
    fun ok() = apply { resultCode = HTTP_OK }

    companion object {
        const val HTTP_OK = 200
    }
}
