package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.core.use_case.OpenInBrowserUseCase
import ru.practicum.android.diploma.domain.team.use_case.LoadDevelopersTeamUseCase

val domainModule = module {

    single {
        LoadDevelopersTeamUseCase(get())
    }
    single {
        OpenInBrowserUseCase(get())
    }
}
