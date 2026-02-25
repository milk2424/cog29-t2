package ru.practicum.android.diploma.data.network

import ru.practicum.android.diploma.data.dto.AppRequest
import ru.practicum.android.diploma.data.dto.Response

interface NetworkClient {
    suspend fun doRequest(dto: AppRequest): Response
}
