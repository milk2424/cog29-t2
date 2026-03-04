package ru.practicum.android.diploma.presentation.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.practicum.android.diploma.presentation.search.SearchUiState

@Composable
fun SearchContent(
    uiState: SearchUiState,
    paddingValues: PaddingValues,
    onQueryChanged: (String) -> Unit,
    onCLearClicked: () -> Unit,
    onLoadNextPage: () -> Unit,
    onVacancyClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchTextField(
            query = uiState.query,
            onQueryChanged = onQueryChanged,
            onClearClicked = onCLearClicked
        )

        SearchState(uiState, onLoadNextPage, onVacancyClick)

    }
}
