package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.interactor.FavoritesInteractor
import ru.practicum.android.diploma.domain.interactor.FavoritesInteractorImpl
import ru.practicum.android.diploma.domain.interactor.FilterInteractor
import ru.practicum.android.diploma.domain.interactor.FilterInteractorImpl
import ru.practicum.android.diploma.domain.interactor.SearchInteractor
import ru.practicum.android.diploma.domain.interactor.SearchInteractorImpl
import ru.practicum.android.diploma.domain.usecase.GetAllCountriesUseCase
import ru.practicum.android.diploma.domain.usecase.GetAllFavoritesUseCase
import ru.practicum.android.diploma.domain.usecase.GetIndustriesUseCase
import ru.practicum.android.diploma.domain.usecase.GetRegionsUseCase
import ru.practicum.android.diploma.domain.usecase.GetVacancyDetailUseCase
import ru.practicum.android.diploma.domain.usecase.LoadDevelopersTeamUseCase
import ru.practicum.android.diploma.domain.usecase.OpenInBrowserUseCase
import ru.practicum.android.diploma.domain.usecase.ShareVacancyUseCase

val domainModule = module {

    single {
        LoadDevelopersTeamUseCase(get())
    }
    single {
        GetVacancyDetailUseCase(get())
    }
    single {
        ShareVacancyUseCase(get(), get())
    }
    single {
        OpenInBrowserUseCase(get())
    }
    single {
        GetAllFavoritesUseCase(get())
    }
    single<SearchInteractor> { SearchInteractorImpl(get(), get()) }

    single<FavoritesInteractor> { FavoritesInteractorImpl(get()) }

    single<FilterInteractor> { FilterInteractorImpl(get()) }

    single { GetAllCountriesUseCase(get()) }

    single { GetRegionsUseCase(get()) }
    single { GetIndustriesUseCase(get()) }
}
