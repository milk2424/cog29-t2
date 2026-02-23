package ru.practicum.android.diploma.di

import com.google.gson.Gson
import org.koin.dsl.module

val coreModule = module {
    single { Gson() }
}
