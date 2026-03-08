package ru.practicum.android.diploma.presentation.filter.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import kotlinx.collections.immutable.toImmutableList
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.ErrorImageWithDescription
import ru.practicum.android.diploma.presentation.common.placeholders.LoadingPlaceholder
import ru.practicum.android.diploma.presentation.filter.region.RegionSelectionScreenState

@Composable
fun RegionState(
    uiState: RegionSelectionScreenState,
    paddingValues: PaddingValues
) {
    when {
        uiState.isLoading && uiState.regions.isEmpty() -> {
            LoadingPlaceholder()
        }

        uiState.isError && uiState.regions.isEmpty() -> {
            ErrorImageWithDescription(
                imageRes = R.drawable.img_cannot_get_list,
                descriptionRes = R.string.cannot_get_list
            )
        }

        uiState.regions.isEmpty() -> {
            ErrorImageWithDescription(
                imageRes = R.drawable.img_cat_error,
                descriptionRes = R.string.region_not_exist
            )
        }

        else -> {
            AreaSelectionList(
                paddingValues = paddingValues,
                items = uiState.regions.toImmutableList(),
                onItemClicked = {}
            )
        }
    }
}
