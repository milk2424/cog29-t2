package ru.practicum.android.diploma.presentation.search

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.Dimens.paddingSmall
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.common.components.ErrorImageWithDescription
import ru.practicum.android.diploma.presentation.common.placeholders.InitialPlaceholder
import ru.practicum.android.diploma.presentation.common.placeholders.LoadingPlaceholder
import ru.practicum.android.diploma.presentation.search.components.SearchTextField
import ru.practicum.android.diploma.presentation.search.components.ShowDescription
import ru.practicum.android.diploma.presentation.search.components.VacancyList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }

    val debouncedOnVacancyClick = { vacancyId: String ->
        navController.navigate("vacancy/$vacancyId") {
            launchSingleTop = true
        }
    }

    AppScaffold(
        title = R.string.tab_main,
        endActions = {
            IconButton(onClick = viewModel::onFilterClicked) {
                Icon(
                    imageVector = ImageVector.vectorResource(R.drawable.filter_off__24px),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = paddingValues.calculateTopPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchTextField(
                query = uiState.query,
                onQueryChanged = viewModel::onQueryChanged,
                onClearClicked = viewModel::onClearClicked
            )

            when {
                uiState.isInitial -> {
                    InitialPlaceholder()
                }

                uiState.isLoading -> {
                    LoadingPlaceholder()
                }

                uiState.isError && uiState.vacancies.isEmpty() -> {
                    ErrorImageWithDescription(
                        imageRes = R.drawable.no_internet,
                        descriptionRes = R.string.no_internet
                    )
                }

                uiState.vacancies.isEmpty() -> {
                    ShowDescription(stringResource(R.string.vacancies_not_exist))
                    ErrorImageWithDescription(
                        imageRes = R.drawable.img_cat_error,
                        descriptionRes = R.string.cannot_get_vacancies_list
                    )
                }

                else -> {
                    if (uiState.foundVacancies > 0) {
                        ShowDescription(
                            stringResource(R.string.founded_vacancies, uiState.foundVacancies)
                        )
                    }
                    Spacer(modifier = Modifier.height(paddingSmall))

                    VacancyList(
                        vacancies = uiState.vacancies.toImmutableList(),
                        onVacancyClick = debouncedOnVacancyClick,
                        onLoadNextPage = viewModel::loadNextPage,
                        isLoadingNextPage = uiState.isLoadingNextPage,
                        paddingValues = PaddingValues()
                    )
                }
            }
        }
    }
}
