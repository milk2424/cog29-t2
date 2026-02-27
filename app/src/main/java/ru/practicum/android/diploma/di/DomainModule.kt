package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.api.SearchInteractor
import ru.practicum.android.diploma.domain.core.usecase.OpenInBrowserUseCase
import ru.practicum.android.diploma.domain.impl.SearchInteractorImpl
import ru.practicum.android.diploma.domain.team.usecase.LoadDevelopersTeamUseCase
import ru.practicum.android.diploma.domain.vacancy.usecases.GetVacancyDetailUseCase

val domainModule = module {

    single {
        LoadDevelopersTeamUseCase(get())
    }
    single {
        GetVacancyDetailUseCase(get())
    }
    single {
        OpenInBrowserUseCase(get())
    }
    single<SearchInteractor> { SearchInteractorImpl(get()) }
}
