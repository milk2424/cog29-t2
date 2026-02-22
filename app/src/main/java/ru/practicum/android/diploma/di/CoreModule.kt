package ru.practicum.android.diploma.di

import org.koin.dsl.module

val coreModule = module {
    single { "DI is working" }
}
