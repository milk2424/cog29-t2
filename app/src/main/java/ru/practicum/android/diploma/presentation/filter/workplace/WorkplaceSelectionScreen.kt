package ru.practicum.android.diploma.presentation.filter.workplace

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.DiplomaTheme
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.filter.components.FilterButton
import ru.practicum.android.diploma.presentation.filter.components.FilterListItem
import ru.practicum.android.diploma.presentation.filter.components.TrailingArrow
import ru.practicum.android.diploma.presentation.filter.components.TrailingClose

@Composable
fun WorkplaceSelectionScreen(
    onStartClick: () -> Unit,
    viewModel: WorkplaceSelectionViewModel = koinViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    WorkplaceSelectionContent(
        state = state,
        onStartClick = onStartClick,
        onCountryClick = {},
        onRegionClick = {},
        onCountryClear = viewModel::onCountryCleared,
        onRegionClear = viewModel::onRegionCleared,
        onConfirm = viewModel::onConfirmSelection
    )
}

@Composable
fun WorkplaceSelectionContent(
    state: WorkplaceSelectionScreenState,
    onStartClick: () -> Unit,
    onCountryClick: () -> Unit,
    onRegionClick: () -> Unit,
    onCountryClear: () -> Unit,
    onRegionClear: () -> Unit,
    onConfirm: () -> Unit
) {
    val hasCountry = state.countryName != null
    val hasRegion = state.regionName != null

    AppScaffold(
        title = R.string.work_place_selection,
        showStartButton = true,
        onStartClick = onStartClick
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
        ) {
            FilterListItem(
                label = R.string.country,
                value = state.countryName,
                isPlaceholder = !hasCountry,
                onClick = onCountryClick,
                trailing = { if (hasCountry) TrailingClose(onCountryClear) else TrailingArrow() }
            )
            FilterListItem(
                label = R.string.region,
                value = state.regionName,
                isPlaceholder = !hasRegion,
                onClick = onRegionClick,
                trailing = { if (hasRegion) TrailingClose(onRegionClear) else TrailingArrow() }
            )
            if (hasCountry || hasRegion) {
                Spacer(modifier = Modifier.weight(1f))
                FilterButton(
                    text = R.string.choose,
                    isPrimary = true,
                    onClick = onConfirm
                )
            }
        }
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewWorkplaceSelectionScreen() {
    DiplomaTheme {
        WorkplaceSelectionContent(
            state = WorkplaceSelectionScreenState(
                countryName = "Россия",
                regionName = null
            ),
            onStartClick = {}, onCountryClick = {},
            onRegionClick = {},
            onCountryClear = {},
            onRegionClear = {},
            onConfirm = {},
        )
    }
}
