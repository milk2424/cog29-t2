package ru.practicum.android.diploma.di

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import ru.practicum.android.diploma.presentation.favorites.FavoritesViewModel
import ru.practicum.android.diploma.presentation.filter.FilterSharedViewModel
import ru.practicum.android.diploma.presentation.filter.FilterViewModel
import ru.practicum.android.diploma.presentation.filter.country.CountrySelectionViewModel
import ru.practicum.android.diploma.presentation.filter.industry.IndustrySelectionViewModel
import ru.practicum.android.diploma.presentation.filter.region.RegionSelectionViewModel
import ru.practicum.android.diploma.presentation.filter.workplace.WorkplaceSelectionViewModel
import ru.practicum.android.diploma.presentation.search.SearchViewModel
import ru.practicum.android.diploma.presentation.team.TeamViewModel
import ru.practicum.android.diploma.presentation.vacancy.VacancyViewModel

val presentationModule = module {
    viewModel {
        TeamViewModel(get(), get())
    }

    viewModel {
        SearchViewModel(get(), get())
    }

    viewModel {
        FavoritesViewModel(get())
    }

    viewModel {
        VacancyViewModel(get(), get(), get())
    }

    viewModel {
        CountrySelectionViewModel(get())
    }

    viewModel { (countryId: String?) ->
        RegionSelectionViewModel(countryId, get())
    }

    viewModel {
        FilterViewModel(get())
    }

    viewModel { WorkplaceSelectionViewModel() }

//    viewModel {
//        IndustrySelectionViewModel(get(), get(), get())
//    }
    viewModel {
            (sharedViewModel: FilterSharedViewModel) ->
        IndustrySelectionViewModel(sharedViewModel, get(), get())
    }
    viewModel {
        FilterSharedViewModel(get())
    }
}
