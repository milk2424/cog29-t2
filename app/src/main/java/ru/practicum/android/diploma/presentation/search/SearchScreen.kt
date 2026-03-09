package ru.practicum.android.diploma.presentation.search

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.filter.FilterSharedViewModel
import ru.practicum.android.diploma.presentation.navigation.Filter
import ru.practicum.android.diploma.presentation.navigation.VacancyDetails
import ru.practicum.android.diploma.presentation.search.components.SearchContent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    navController: NavController,
    sharedViewModel: FilterSharedViewModel,
    viewModel: SearchViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val hasFilter by viewModel.hasFilter.collectAsStateWithLifecycle()
    val sharedFilter by sharedViewModel.filter.collectAsStateWithLifecycle()

    LaunchedEffect(sharedFilter) {
        if (sharedFilter != viewModel.lastAppliedFilter) {
            viewModel.refreshSearch(sharedFilter)
        }
    }
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when (event) {
                is SearchViewModel.SearchEvent.ShowError -> {
                    Toast.makeText(context, event.messageRes, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    AppScaffold(
        title = R.string.tab_main,
        endActions = {
            IconButton(
                onClick = { navController.navigate(Filter) }
            ) {
                Icon(
                    imageVector = ImageVector.vectorResource(
                        if (hasFilter) {
                            R.drawable.filter_on__24px
                        } else {
                            R.drawable.filter_off__24px
                        }

                    ),
                    contentDescription = null,
                    tint = if (hasFilter) {
                        Color.Unspecified
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    }
                )
            }
        }
    ) { paddingValues ->

        SearchContent(
            uiState = uiState,
            paddingValues = paddingValues,
            onQueryChanged = viewModel::onQueryChanged,
            onClearClicked = viewModel::onClearClicked,
            onLoadNextPage = viewModel::loadNextPage,
            onVacancyClick = { vacancyId ->
                navController.navigate(VacancyDetails(id = vacancyId)) {
                    launchSingleTop = true
                }
            }

        )
    }
}
