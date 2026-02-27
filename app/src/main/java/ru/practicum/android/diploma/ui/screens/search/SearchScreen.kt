package ru.practicum.android.diploma.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.collections.immutable.toImmutableList
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.search.SearchViewModel
import ru.practicum.android.diploma.ui.core.uielements.ErrorImageWithDescription
import ru.practicum.android.diploma.ui.placeholders.InitialPlaceholder
import ru.practicum.android.diploma.ui.placeholders.LoadingPlaceholder
import ru.practicum.android.diploma.ui.screens.search.uielements.VacancyList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController, viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

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
        containerColor = MaterialTheme.colorScheme.background, topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = stringResource(R.string.tab_main),
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        IconButton(onClick = viewModel::onFilterClicked) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.filter_off__24px),
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onBackground
                            )
                        }
                    }

                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                windowInsets = WindowInsets(top = Dimens.insetsZero)
            )
        }) { paddingValues ->

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

                uiState.isError -> {
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
                    ShowDescription(stringResource(R.string.founded_vacancies, uiState.vacancies.size))
                    Spacer(modifier = Modifier.height(8.dp))
                    VacancyList(
                        vacancies = uiState.vacancies.toImmutableList(),
                        onVacancyClick = debouncedOnVacancyClick,
                        paddingValues = PaddingValues()
                    )
                }
            }
        }
    }
}

@Composable
private fun ShowDescription(
    message: String
) {
    Box(
        modifier = Modifier
            .padding(top = 3.dp)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(12.dp)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = 12.dp, vertical = 4.dp),
            text = message,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

private const val CLICK_DEBOUNCE_DELAY = 1_000L
