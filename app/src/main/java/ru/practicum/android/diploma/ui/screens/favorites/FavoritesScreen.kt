package ru.practicum.android.diploma.ui.screens.favorites

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.favorites.FavoritesViewModel
import ru.practicum.android.diploma.ui.core.uielements.ErrorImageWithDescription
import ru.practicum.android.diploma.ui.screens.search.uielements.VacancyList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel = koinViewModel()) {
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
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.background)
            )
        }
    ) { paddingValues ->
        //данные есть в бд
        VacancyList(emptyList(), paddingValues)
        //в бд пусто
        ErrorImageWithDescription(R.drawable.img_favourites_empty_list, R.string.list_is_empty)
        //ошибка при запросе в бд
        ErrorImageWithDescription(R.drawable.img_cat_error, R.string.cannot_get_vacancies_list)
    }

}
