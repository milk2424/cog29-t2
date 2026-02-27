package ru.practicum.android.diploma.di

import org.koin.dsl.module
import ru.practicum.android.diploma.domain.api.FavoritesInteractor
import ru.practicum.android.diploma.domain.api.SearchInteractor
import ru.practicum.android.diploma.domain.impl.FavoritesInteractorImpl
import ru.practicum.android.diploma.domain.impl.SearchInteractorImpl

val domainModule = module {
    single<SearchInteractor> { SearchInteractorImpl(get()) }

    single<FavoritesInteractor> { FavoritesInteractorImpl(get()) }
}
