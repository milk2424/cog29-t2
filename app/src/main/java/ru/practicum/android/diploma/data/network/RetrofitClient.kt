package ru.practicum.android.diploma.data.network

import ru.practicum.android.diploma.data.dto.Response

class RetrofitClient(apiService: ApiService): NetworkClient {
    val hhService: ApiService = apiService

    override suspend fun doRequest(dto: Any): Response {

    }

}
