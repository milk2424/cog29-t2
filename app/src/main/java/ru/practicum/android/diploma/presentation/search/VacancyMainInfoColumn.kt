package ru.practicum.android.diploma.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import ru.practicum.android.diploma.core.utils.SalaryStrings
import ru.practicum.android.diploma.core.utils.formatSalaryRange
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.presentation.favorites.mapVacancyNameAndArea

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
