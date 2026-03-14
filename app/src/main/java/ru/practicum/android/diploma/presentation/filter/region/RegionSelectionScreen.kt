package ru.practicum.android.diploma.presentation.filter.region

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.filter.FilterSharedViewModel
import ru.practicum.android.diploma.presentation.filter.components.RegionContent

@Composable
fun RegionSelectionScreen(
    countryId: String?,
    sharedViewModel: FilterSharedViewModel,
    onNavigateBack: () -> Unit,
    viewModel: RegionSelectionViewModel = koinViewModel(
        parameters = { parametersOf(countryId) }
    )
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AppScaffold(
        title = R.string.region_choosing,
        showStartButton = true,
        onStartClick = onNavigateBack
    ) { paddingValues ->
        RegionContent(
            uiState = uiState,
            paddingValues = paddingValues,
            onQueryChanged = viewModel::onQueryChanged,
            onClearClicked = viewModel::onClearClicked,
            onRegionClick = { region ->
                if (sharedViewModel.filter.value.countryId == null) {
                    viewModel.getCountryName(region.parentId ?: "") { countryName ->
                        sharedViewModel.setCountry(
                            countryId = region.parentId?.toInt(),
                            countryName = countryName
                        )
                        sharedViewModel.setRegion(
                            regionId = region.id.toInt(),
                            regionName = region.name
                        )
                        onNavigateBack()
                    }
                } else {
                    onNavigateBack()
                }
            }
        )
    }
}
