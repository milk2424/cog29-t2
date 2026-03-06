package ru.practicum.android.diploma.presentation.filter.country

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.common.components.AreaSelectionItem
import ru.practicum.android.diploma.presentation.common.components.ErrorImageWithDescription
import ru.practicum.android.diploma.presentation.common.placeholders.LoadingPlaceholder

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
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
                    items(
                        items = currentState.countries,
                        key = { it.id }
                    ) { country ->
                        AreaSelectionItem(country.name) {
                            // Передача country назад в Фильтр
                        }
                    }
                }
        }
    }
}
