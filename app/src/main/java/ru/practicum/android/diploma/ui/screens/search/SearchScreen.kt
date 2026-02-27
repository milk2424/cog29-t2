package ru.practicum.android.diploma.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.ui.placeholders.InitialPlaceholder
import ru.practicum.android.diploma.ui.placeholders.Loading
import ru.practicum.android.diploma.ui.theme.Dimens.paddingMedium

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = koinViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingMedium)
    ) {
        SearchHeader(
            onFilterClick = viewModel::onFilterClicked
        )

        SearchTextField(
            query = uiState.query,
            onQueryChanged = viewModel::onQueryChanged,
            onClearClicked = viewModel::onClearClicked
        )

        when {
            uiState.isLoading -> {
                Loading()
            }

            uiState.isInitial -> {
                InitialPlaceholder()
            }

            else -> {}
        }
    }
}
