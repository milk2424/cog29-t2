package ru.practicum.android.diploma.ui.screens.search.uielements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.presentation.extensions.salaryStrings
import ru.practicum.android.diploma.ui.theme.Dimens.paddingLarge

@Composable
fun VacancyList(
    vacancies: ImmutableList<Vacancy>,
    paddingValues: PaddingValues,
    onVacancyClicked: (id: String) -> Unit
) {
    val salaryStrings = salaryStrings()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = paddingLarge),
    ) {
        items(
            items = vacancies,
            key = { it.id }
        ) { vacancy ->
            VacancyListItem(vacancy, salaryStrings) { id ->
                onVacancyClicked(id)
            }
        }
    }
}
