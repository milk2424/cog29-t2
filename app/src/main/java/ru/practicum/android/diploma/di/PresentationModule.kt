package ru.practicum.android.diploma.di

import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.presentation.favorites.FavoritesViewModel
import ru.practicum.android.diploma.presentation.search.SearchViewModel
import ru.practicum.android.diploma.presentation.team.TeamViewModel
import ru.practicum.android.diploma.presentation.vacancy.VacancyViewModel

val presentationModule = module {
    viewModel {
        TeamViewModel(get(), get())
    }

    viewModel {
        SearchViewModel(
            searchInteractor = get(),
            context = androidContext()
        )
    }

    viewModel {
        FavoritesViewModel(get())
    }

    viewModel {
        VacancyViewModel(get(), get(), get())
    }
}
