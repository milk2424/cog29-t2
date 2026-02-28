package ru.practicum.android.diploma.ui.screens.vacancy

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.presentation.extensions.salaryStrings
import ru.practicum.android.diploma.ui.preview.PreviewData
import ru.practicum.android.diploma.ui.theme.DiplomaTheme
import ru.practicum.android.diploma.util.Currency
import ru.practicum.android.diploma.util.formatSalaryRange


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

    val showActions = state is VacancyScreenState.Content ||
        (state is VacancyScreenState.Error && state.type == VacancyScreenState.ErrorType.NOT_FOUND)
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
                .padding(0.dp, 16.dp, 0.dp, 0.dp)
        ) {
            when (state) {
                is VacancyScreenState.Loading -> { /* спиннер */
                }

                is VacancyScreenState.Error -> { /* плейсхолдер по state.type */
                }

                is VacancyScreenState.Content -> {
                    ContentBody(state.vacancy)
                }
            }
        }

    }
}

@Composable
fun ContentBody(vacancy: Vacancy) {
    val salaryStrings = salaryStrings()
    val currencySymbol = vacancy.salary?.currency?.let { code ->
        runCatching { Currency.valueOf(code).symbol }.getOrDefault(code)
    }
    Column(modifier = Modifier.padding(16.dp, 0.dp)) {
        Text(
            vacancy.name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
        )
        Text(
            text = formatSalaryRange(
                vacancy.salary?.from,
                max = vacancy.salary?.to,
                currency = currencySymbol,
                strings = salaryStrings
            ),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
        )
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 24.dp, 0.dp, 0.dp),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // лого
                AsyncImage(
                    model = vacancy.employer.logo,
                    contentDescription = null,
                    placeholder = painterResource(R.drawable.img_no_employer_logo),
                    error = painterResource(R.drawable.img_no_employer_logo),
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        vacancy.employer.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        vacancy.areaName,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyTopBar(
    showActions: Boolean,
    actionsEnabled: Boolean,
    favoriteIcon: Int,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(painterResource(R.drawable.arrow_back_24px), contentDescription = "")
            }
        },
        title = {
            Text(
                stringResource(id = R.string.vacancy),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(4.dp, 0.dp, 0.dp, 0.dp),
            )
        },
        actions = {
            if (showActions) {
                IconButton(
                    onClick = onShareClick,
                    enabled = actionsEnabled
                )
                {
                    Icon(
                        painterResource(R.drawable.sharing_24px),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = onFavoriteClick,
                    enabled = actionsEnabled
                )
                {
                    Icon(
                        painterResource(favoriteIcon),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "",
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}


@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
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
