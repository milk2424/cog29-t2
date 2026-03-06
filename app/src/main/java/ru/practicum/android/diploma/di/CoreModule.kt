package ru.practicum.android.diploma.di

import com.google.gson.Gson
import org.koin.dsl.module
import ru.practicum.android.diploma.core.logging.AppLogger
import ru.practicum.android.diploma.core.logging.AppLoggerImpl

val coreModule = module {
    single { Gson() }

    single<AppLogger> { AppLoggerImpl() }
}
