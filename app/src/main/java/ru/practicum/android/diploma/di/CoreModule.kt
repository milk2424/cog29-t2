package ru.practicum.android.diploma.di

import com.google.gson.Gson
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.practicum.android.diploma.core.logging.AppLogger
import ru.practicum.android.diploma.core.logging.AppLoggerImpl
import ru.practicum.android.diploma.core.storage.FilterStorage
import ru.practicum.android.diploma.core.storage.FilterStorageImpl

private const val FILTER_SETTINGS = "filter_settings"

val coreModule = module {
    single { Gson() }

    single<AppLogger> { AppLoggerImpl() }

    single {
        androidContext().getSharedPreferences(FILTER_SETTINGS, android.content.Context.MODE_PRIVATE)
    }

    single<FilterStorage> { FilterStorageImpl(get()) }
}
