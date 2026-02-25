package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.ui.screens.search.SearchViewModel

val presentationModule = module {
    viewModel {
        SearchViewModel()
    }
}
