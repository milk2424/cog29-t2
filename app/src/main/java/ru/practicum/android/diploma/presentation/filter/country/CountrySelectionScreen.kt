package ru.practicum.android.diploma.presentation.filter.country

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.common.components.ErrorImageWithDescription
import ru.practicum.android.diploma.presentation.common.placeholders.LoadingPlaceholder
import ru.practicum.android.diploma.presentation.filter.components.AreaSelectionList

@Composable
fun CountrySelectionScreen(
    onNavigateBack: () -> Unit,
    viewModel: CountrySelectionViewModel = koinViewModel()
) {
    val state by viewModel.screenState.collectAsStateWithLifecycle()

    AppScaffold(
        title = R.string.country_choosing,
        showStartButton = true,
        onStartClick = onNavigateBack
    ) { paddingValues ->

        when (val currentState = state) {
            is CountrySelectionScreenState.Error -> ErrorImageWithDescription(
                imageRes = R.drawable.img_cannot_get_list,
                descriptionRes = R.string.cannot_get_list
            )

            is CountrySelectionScreenState.Loading -> LoadingPlaceholder()
            is CountrySelectionScreenState.Success ->
                AreaSelectionList(paddingValues, currentState.countries) {
                    // Передача страны назад в фильтр
                }
        }
    }
}
