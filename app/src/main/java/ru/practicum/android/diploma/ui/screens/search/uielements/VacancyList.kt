package ru.practicum.android.diploma.ui.screens.search.uielements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.presentation.extensions.salaryStrings
import ru.practicum.android.diploma.ui.theme.Dimens
import ru.practicum.android.diploma.ui.theme.Dimens.paddingLarge

@Composable
fun VacancyList(
    vacancies: ImmutableList<Vacancy>,
    onVacancyClick: (String) -> Unit,
    paddingValues: PaddingValues,
    onLoadNextPage: (() -> Unit)? = null,
    isLoadingNextPage: Boolean = false
) {
    val listState = rememberLazyListState()
    val salaryStrings = salaryStrings()

    val shouldLoadNext by remember {
        derivedStateOf {
            if (onLoadNextPage != null) {
                val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index
                val totalItemsCount = listState.layoutInfo.totalItemsCount

                lastVisibleItemIndex != null &&
                    lastVisibleItemIndex >= totalItemsCount - 1 &&
                    !isLoadingNextPage &&
                    vacancies.isNotEmpty()
            } else {
                false
            }
        }
    }

    LaunchedEffect(shouldLoadNext) {
        if (shouldLoadNext) {
            onLoadNextPage?.invoke()
        }
    }

    LazyColumn(
        state = listState,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = paddingLarge)
    ) {
        items(
            items = vacancies,
            key = { it.id }
        ) { vacancy ->
            VacancyListItem(
                vacancy = vacancy,
                salaryStrings = salaryStrings,
                onVacancyClicked = onVacancyClick
            )
        }

        if (isLoadingNextPage && vacancies.isNotEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.paginationVerticalPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(Dimens.paginationProgressSize),
                        color = MaterialTheme.colorScheme.primary,
                        strokeWidth = Dimens.paginationProgressStroke
                    )
                }
            }
        }
    }
}
