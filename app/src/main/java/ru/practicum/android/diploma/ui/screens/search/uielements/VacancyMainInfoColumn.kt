package ru.practicum.android.diploma.ui.screens.search.uielements

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.presentation.favorites.utils.mapVacancyNameAndArea
import ru.practicum.android.diploma.util.SalaryStrings
import ru.practicum.android.diploma.util.formatSalaryRange

@Composable
fun VacancyMainInfoColumn(vacancy: Vacancy, salaryStrings: SalaryStrings) {
    Column {
        with(vacancy) {
            Text(
                text = mapVacancyNameAndArea(name, areaName),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = vacancy.employer.name,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = formatSalaryRange(
                    min = salary?.from,
                    max = salary?.to,
                    currency = salary?.currency,
                    strings = salaryStrings
                ),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
