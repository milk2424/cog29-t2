package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.core.usecase.OpenInBrowserUseCase
import ru.practicum.android.diploma.domain.team.usecase.LoadDevelopersTeamUseCase

val domainModule = module {

    single {
        LoadDevelopersTeamUseCase(get())
    }
    single {
        OpenInBrowserUseCase(get())
    }
}
