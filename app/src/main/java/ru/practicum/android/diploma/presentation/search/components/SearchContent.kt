package ru.practicum.android.diploma.presentation.search.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.search.SearchUiState

@Composable
fun SearchContent(
    uiState: SearchUiState,
    paddingValues: PaddingValues,
    onQueryChanged: (String) -> Unit,
    onClearClicked: () -> Unit,
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
            placeholderText = stringResource(R.string.search_placeholder),
            onQueryChanged = onQueryChanged,
            onClearClicked = onClearClicked
        )

        SearchState(uiState, onLoadNextPage, onVacancyClick)

    }
}
