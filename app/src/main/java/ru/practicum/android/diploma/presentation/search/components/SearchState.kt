package ru.practicum.android.diploma.presentation.search.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.collections.immutable.toImmutableList
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.ErrorImageWithDescription
import ru.practicum.android.diploma.presentation.common.placeholders.InitialPlaceholder
import ru.practicum.android.diploma.presentation.common.placeholders.LoadingPlaceholder
import ru.practicum.android.diploma.presentation.search.SearchUiState

@Composable
fun SearchState(
    uiState: SearchUiState,
    onLoadNextPage: () -> Unit,
    onVacancyClick: (String) -> Unit
) {
    when {
        uiState.isInitial -> {
            InitialPlaceholder()
        }

        uiState.isDebouncing -> {
            Box(modifier = Modifier.fillMaxSize())
        }

        uiState.isLoading && uiState.vacancies.isEmpty() -> {
            LoadingPlaceholder()
        }

        uiState.isError && uiState.vacancies.isEmpty() -> {
            ErrorImageWithDescription(
                imageRes = R.drawable.no_internet,
                descriptionRes = R.string.no_internet
            )
        }

        uiState.vacancies.isEmpty() -> {
            ShowDescription(stringResource(R.string.vacancies_not_exist))
            ErrorImageWithDescription(
                imageRes = R.drawable.img_cat_error,
                descriptionRes = R.string.cannot_get_vacancies_list
            )
        }

        else -> {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                VacancyList(
                    vacancies = uiState.vacancies.toImmutableList(),
                    onVacancyClick = onVacancyClick,
                    onLoadNextPage = onLoadNextPage,
                    isLoadingNextPage = uiState.isLoadingNextPage,
                    paddingValues = PaddingValues(),
                    foundVacancies = uiState.foundVacancies
                )
            }
        }
    }
}
