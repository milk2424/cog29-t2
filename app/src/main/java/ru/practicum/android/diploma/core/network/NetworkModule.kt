package ru.practicum.android.diploma.core.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

object NetworkModule {

    val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(UserAgentInterceptor())
            .build()
    }

    private class UserAgentInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .header("User-Agent", "DiplomaApp/1.0")
                .build()
            return chain.proceed(request)
        }
    }
}
