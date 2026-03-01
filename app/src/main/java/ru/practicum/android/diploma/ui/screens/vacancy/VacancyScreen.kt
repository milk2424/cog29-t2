package ru.practicum.android.diploma.ui.screens.vacancy

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.placeholders.LoadingPlaceholder
import ru.practicum.android.diploma.ui.preview.PreviewData
import ru.practicum.android.diploma.ui.screens.vacancy.uielements.ContentBody
import ru.practicum.android.diploma.ui.screens.vacancy.uielements.VacancyTopBar
import ru.practicum.android.diploma.ui.theme.Dimens.paddingLarge
import ru.practicum.android.diploma.ui.theme.DiplomaTheme

@Composable
fun VacancyScreen(
    state: VacancyScreenState,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    val favoriteIcon = when (state) {
        is VacancyScreenState.Content -> if (state.isFavorite) {
            R.drawable.favorites_on__24px
        } else {
            R.drawable.favorites_off__24px
        }

        else -> R.drawable.favorites_off__24px
    }
    val showActions = state is VacancyScreenState.Content || state is VacancyScreenState.NotFound
    val actionsEnabled = state is VacancyScreenState.Content
    Scaffold(
        topBar = {
            VacancyTopBar(
                showActions = showActions,
                actionsEnabled = actionsEnabled,
                favoriteIcon = favoriteIcon,
                onBackClick = onBackClick,
                onShareClick = onShareClick,
                onFavoriteClick = onFavoriteClick
            )
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
                        Text(
                            text = stringResource(R.string.server_error),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }

                is VacancyScreenState.NotFound -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.vacancy_not_found),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                        )
                    }
                }

                is VacancyScreenState.Content -> {
                    ContentBody(state.vacancy)
                }
            }
        }
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, widthDp = 360, heightDp = 1400)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewVacancyScreen() {
    DiplomaTheme {
        VacancyScreen(
            state = VacancyScreenState.Content(PreviewData.previewVacancy, false),
            onBackClick = {},
            onShareClick = {},
            onFavoriteClick = {},
        )
    }
}
