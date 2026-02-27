package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.presentation.favorites.FavoritesViewModel
import ru.practicum.android.diploma.presentation.team.TeamViewModel
import ru.practicum.android.diploma.presentation.search.SearchViewModel

val presentationModule = module {
    viewModel {
        TeamViewModel(get(), get())
    }

    viewModel {
        SearchViewModel(searchInteractor = get())
    }

    viewModel {
        FavoritesViewModel()
    }
}
