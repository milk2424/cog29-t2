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
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import kotlinx.collections.immutable.persistentListOf
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.favorites.FavoritesViewModel
import ru.practicum.android.diploma.ui.core.uielements.ErrorImageWithDescription
import ru.practicum.android.diploma.ui.screens.search.uielements.VacancyList
import ru.practicum.android.diploma.ui.theme.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritesScreen(navController: NavController, viewModel: FavoritesViewModel = koinViewModel()) {
    var clickTimer by remember { mutableLongStateOf(0L) }
    val debouncedOnVacancyClick = remember(CLICK_DEBOUNCE_DELAY) {
        { vacancyId: String ->
            val currentTime = System.currentTimeMillis()
            if (currentTime - clickTimer >= CLICK_DEBOUNCE_DELAY) {
                navController.navigate("vacancy/$vacancyId")
            }
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
        }
    ) { paddingValues ->
        // данные есть в бд
        VacancyList(persistentListOf(), debouncedOnVacancyClick, paddingValues)
        // в бд пусто
        ErrorImageWithDescription(R.drawable.img_favourites_empty_list, R.string.list_is_empty)
        // ошибка при запросе в бд
        ErrorImageWithDescription(R.drawable.img_cat_error, R.string.cannot_get_vacancies_list)
    }
}

private const val CLICK_DEBOUNCE_DELAY = 1_000L
