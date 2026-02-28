package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.api.FavoritesInteractor
import ru.practicum.android.diploma.domain.api.SearchInteractor
import ru.practicum.android.diploma.domain.core.usecase.OpenInBrowserUseCase
import ru.practicum.android.diploma.domain.favorites.usecase.GetAllFavoritesUseCase
import ru.practicum.android.diploma.domain.impl.FavoritesInteractorImpl
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
    single {
        GetAllFavoritesUseCase(get())
    }
    single<SearchInteractor> { SearchInteractorImpl(get()) }

    single<FavoritesInteractor> { FavoritesInteractorImpl(get()) }
}
