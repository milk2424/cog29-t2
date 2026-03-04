package ru.practicum.android.diploma.presentation.common.components

import android.content.Context
import coil.ImageLoader
import ru.practicum.android.diploma.core.network.NetworkModule

object AppImageLoader {
    fun get(context: Context): ImageLoader {
        return ImageLoader.Builder(context)
            .okHttpClient { NetworkModule.okHttpClient }
            .crossfade(true)
            .build()
    }
}
