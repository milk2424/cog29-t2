package ru.practicum.android.diploma.data.dto

import ru.practicum.android.diploma.data.network.HttpCodes.HTTP_OK

open class Response {
    var resultCode = -1
    fun ok() = apply { resultCode = HTTP_OK }
}
