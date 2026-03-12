package ru.practicum.android.diploma.presentation.filter.workplace

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.filter.FilterSharedViewModel
import ru.practicum.android.diploma.presentation.filter.components.FilterButton
import ru.practicum.android.diploma.presentation.filter.components.FilterListItem
import ru.practicum.android.diploma.presentation.filter.components.TrailingArrow
import ru.practicum.android.diploma.presentation.filter.components.TrailingClose

@Composable
fun WorkplaceSelectionScreen(
    sharedViewModel: FilterSharedViewModel,
    onStartClick: () -> Unit,
    onCountryClick: () -> Unit,
    onRegionClick: () -> Unit,
    navController: NavController
) {
    val filter by sharedViewModel.filter.collectAsStateWithLifecycle()

    WorkplaceSelectionContent(
        countryName = filter.countryName,
        regionName = filter.regionName,
        onStartClick = onStartClick,
        onCountryClick = onCountryClick,
        onRegionClick = onRegionClick,
        onCountryCleared = {
            sharedViewModel.setCountry(null, null)
        },
        onRegionCleared = {
            sharedViewModel.setRegion(null, null)
        },
        onConfirm = {
            navController.popBackStack()
        }
    )
}

@Composable
fun WorkplaceSelectionContent(
    countryName: String?,
    regionName: String?,
    onStartClick: () -> Unit,
    onCountryClick: () -> Unit,
    onRegionClick: () -> Unit,
    onCountryCleared: () -> Unit,
    onRegionCleared: () -> Unit,
    onConfirm: () -> Unit,
) {
    val hasCountry = countryName != null
    val hasRegion = regionName != null

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
                value = countryName,
                isPlaceholder = !hasCountry,
                onClick = onCountryClick,
                trailing = { if (hasCountry) TrailingClose { onCountryCleared() } else TrailingArrow() }
            )
            FilterListItem(
                label = R.string.region,
                value = regionName,
                isPlaceholder = !hasRegion,
                onClick = onRegionClick,
                trailing = { if (hasRegion) TrailingClose { onRegionCleared() } else TrailingArrow() }
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
