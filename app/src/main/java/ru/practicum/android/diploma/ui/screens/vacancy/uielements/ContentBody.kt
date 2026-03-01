package ru.practicum.android.diploma.ui.screens.vacancy.uielements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.presentation.extensions.salaryStrings
import ru.practicum.android.diploma.util.Currency
import ru.practicum.android.diploma.util.formatSalaryRange

@Composable
fun ContentBody(vacancy: Vacancy) {
    val salaryStrings = salaryStrings()
    val currencySymbol = vacancy.salary?.currency?.let { code ->
        runCatching { Currency.valueOf(code).symbol }.getOrDefault(code)
    }
    Column(
        modifier = Modifier
            .padding(16.dp, 0.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            vacancy.name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
        )
        Text(
            text = formatSalaryRange(
                vacancy.salary?.from,
                max = vacancy.salary?.to,
                currency = currencySymbol,
                strings = salaryStrings
            ),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp),
        )
        EmployerCard(vacancy)
        Spacer(modifier = Modifier.height(24.dp))
        vacancy.experience?.let {
            Text(
                stringResource(R.string.required_experience),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                it,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        val scheduleInfo = listOfNotNull(
            vacancy.employment,
            vacancy.schedule
        )
            .joinToString(", ")
        if (scheduleInfo.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                scheduleInfo,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            stringResource(R.string.vacancy_description),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        DescriptionBlock(vacancy.description)
        if (vacancy.skills.isNotEmpty()) {
            SkillsBlock(vacancy.skills)
        }
        vacancy.contacts?.let { contacts ->
            ContactsBlock(contacts)
        }
    }
}
