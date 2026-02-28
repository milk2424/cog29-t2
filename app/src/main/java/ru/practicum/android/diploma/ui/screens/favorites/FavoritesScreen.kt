package ru.practicum.android.diploma.ui.screens.favorites

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.favorites.FavoritesScreenState
import ru.practicum.android.diploma.presentation.favorites.FavoritesViewModel
import ru.practicum.android.diploma.ui.core.uielements.ErrorImageWithDescription
import ru.practicum.android.diploma.ui.placeholders.Loading
import ru.practicum.android.diploma.ui.screens.search.uielements.VacancyList
import ru.practicum.android.diploma.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoritesViewModel = koinViewModel()
) {
    val debouncedOnVacancyClick = { vacancyId: String ->
        navController.navigate("vacancy/$vacancyId") {
            launchSingleTop = true
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.tab_favorites),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background),
                windowInsets = WindowInsets(top = Dimens.insetsZero)
            )
            
            VacancyList(persistentListOf(), debouncedOnVacancyClick, paddingValues)
            
        }
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

            is FavoritesScreenState.Loading -> Loading()

            is FavoritesScreenState.Success -> {
                VacancyList(persistentListOf(), debouncedOnVacancyClick, paddingValues)
            }
        }
    }
}
