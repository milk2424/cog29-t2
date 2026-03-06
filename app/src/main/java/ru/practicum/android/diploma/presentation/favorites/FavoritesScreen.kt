package ru.practicum.android.diploma.presentation.favorites

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.common.components.ErrorImageWithDescription
import ru.practicum.android.diploma.presentation.common.placeholders.LoadingPlaceholder
import ru.practicum.android.diploma.presentation.search.components.VacancyList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel = koinViewModel()) {
    val screenState by viewModel.screenState.collectAsStateWithLifecycle()
    AppScaffold(
        title = R.string.tab_favorites
    ) { paddingValues ->
        when (val state = screenState) {
            is FavoritesScreenState.Empty -> ErrorImageWithDescription(
                R.drawable.img_favourites_empty_list,
                R.string.list_is_empty
            )

            is FavoritesScreenState.Error -> ErrorImageWithDescription(
                R.drawable.img_cat_error,
                R.string.cannot_get_vacancies_list
            )

            is FavoritesScreenState.Loading -> LoadingPlaceholder()

            is FavoritesScreenState.Success -> {
                VacancyList(
                    vacancies = state.vacancies,
                    onVacancyClick = { vacancyId ->
                        navController.navigate("vacancy/$vacancyId") {
                            launchSingleTop = true
                        }
                    },
                    paddingValues = paddingValues,
                    foundVacancies = state.vacancies.size
                )
            }
        }
    }
}
