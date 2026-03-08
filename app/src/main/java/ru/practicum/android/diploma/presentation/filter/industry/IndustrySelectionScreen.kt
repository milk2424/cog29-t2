package ru.practicum.android.diploma.presentation.filter.industry

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.common.components.ErrorImageWithDescription
import ru.practicum.android.diploma.presentation.common.placeholders.LoadingPlaceholder
import ru.practicum.android.diploma.presentation.filter.FilterSharedViewModel
import ru.practicum.android.diploma.presentation.filter.industry.components.IndustryListItem
import ru.practicum.android.diploma.presentation.filter.industry.components.IndustrySearchTextField

@Composable
fun IndustrySelectionScreen(
    sharedViewModel: FilterSharedViewModel,
    navController: NavController,
    viewModel: IndustrySelectionViewModel = koinViewModel(parameters = { parametersOf(sharedViewModel) })
) {
    val filter by sharedViewModel.filter.collectAsStateWithLifecycle()
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()

    AppScaffold(
        title = R.string.industry_choosing,
        showStartButton = true,
        onStartClick = {
            navController.popBackStack()
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (val currentState = state) {
                is IndustrySelectionScreenState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        LoadingPlaceholder()
                    }
                }

                is IndustrySelectionScreenState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ErrorImageWithDescription(
                            imageRes = R.drawable.img_cannot_get_list,
                            descriptionRes = R.string.cannot_get_list
                        )
                    }
                }

                is IndustrySelectionScreenState.EmptySearch -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        IndustrySearchTextField(
                            query = searchQuery,
                            onQueryChanged = viewModel::onSearchQueryChanged,
                            onClearClicked = viewModel::onClearSearchClicked
                        )

                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            ErrorImageWithDescription(
                                imageRes = R.drawable.img_cannot_get_list,
                                descriptionRes = R.string.industry_not_exist
                            )
                        }
                    }
                }

                is IndustrySelectionScreenState.Success -> {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        IndustrySearchTextField(
                            query = searchQuery,
                            onQueryChanged = viewModel::onSearchQueryChanged,
                            onClearClicked = viewModel::onClearSearchClicked
                        )

                        if (currentState.selectedIndustryId != null) {
                            LazyColumn(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxWidth()
                            ) {
                                items(
                                    items = currentState.industries,
                                    key = { it.id }
                                ) { industry ->
                                    IndustryListItem(
                                        industry = industry,
                                        isSelected = industry.id == currentState.selectedIndustryId,
                                        onItemClicked = viewModel::onIndustrySelected
                                    )
                                }
                            }

                            Button(
                                onClick = {
                                    viewModel.onConfirmSelection()
                                    navController.popBackStack()
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 17.dp, vertical = 24.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.primary
                                ),
                                shape = MaterialTheme.shapes.medium
                            ) {
                                Text(
                                    text = stringResource(R.string.choose),
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    modifier = Modifier.padding(vertical = 8.dp)
                                )
                            }
                        } else {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .fillMaxWidth()
                            ) {
                                items(
                                    items = currentState.industries,
                                    key = { it.id }
                                ) { industry ->
                                    IndustryListItem(
                                        industry = industry,
                                        isSelected = false,
                                        onItemClicked = viewModel::onIndustrySelected
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
