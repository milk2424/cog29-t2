package ru.practicum.android.diploma.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Проверка доступности сети(вайфай, мобильная или кабель)
 * возращает true если есть доступ к интернету
 */

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

    val network = connectivityManager?.activeNetwork
    val capabilities = connectivityManager?.getNetworkCapabilities(network)

    return capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true ||
        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true ||
        capabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) == true
}
