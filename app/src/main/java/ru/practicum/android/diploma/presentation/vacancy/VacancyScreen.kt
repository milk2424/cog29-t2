package ru.practicum.android.diploma.presentation.vacancy

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.Dimens.paddingLarge
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.common.placeholders.LoadingPlaceholder
import ru.practicum.android.diploma.presentation.vacancy.components.ContentBody
import ru.practicum.android.diploma.presentation.vacancy.components.TitleBlock

@Composable
fun VacancyScreen(
    navController: NavController,
    vacancyId: String,
    viewModel: VacancyViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(vacancyId) {
        viewModel.loadVacancy(vacancyId)
    }

    val onBackClick: () -> Unit = {
        navController.popBackStack()
    }

    val onShareClick = {
        viewModel.shareVacancy()
    }

    val onFavoriteClick = {
        viewModel.toggleFavorite()
    }

    val showActions = state is VacancyScreenState.Content || state is VacancyScreenState.NotFound
    val actionsEnabled = state is VacancyScreenState.Content

    AppScaffold(
        title = R.string.vacancy,
        showStartButton = true,
        onStartClick = onBackClick,
        endActions = {
            if (showActions) {
                IconButton(
                    onClick = onShareClick,
                    enabled = actionsEnabled
                ) {
                    Icon(
                        painter = painterResource(R.drawable.sharing_24px),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                }
                IconButton(
                    onClick = onFavoriteClick,
                    enabled = actionsEnabled
                ) {
                    when (state) {
                        is VacancyScreenState.Content ->
                            if ((state as VacancyScreenState.Content).isFavorite) {
                                Icon(
                                    painter = painterResource(R.drawable.favorites_on__24px),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.error
                                )
                            } else {
                                Icon(
                                    painter = painterResource(R.drawable.favorites_off__24px),
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.onBackground
                                )
                            }

                        else -> Icon(
                            painter = painterResource(R.drawable.favorites_off__24px),
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(top = paddingLarge)
        ) {
            when (state) {
                is VacancyScreenState.Loading -> {
                    LoadingPlaceholder()
                }

                is VacancyScreenState.ServerError -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        TitleBlock(R.string.server_error)
                    }
                }

                is VacancyScreenState.NotFound -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        TitleBlock(R.string.vacancy_not_found)
                    }
                }

                is VacancyScreenState.Content -> {
                    ContentBody((state as VacancyScreenState.Content).vacancy)
                }
            }
        }
    }
}
