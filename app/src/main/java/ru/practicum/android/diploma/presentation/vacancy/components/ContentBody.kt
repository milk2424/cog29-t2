package ru.practicum.android.diploma.presentation.vacancy.components

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
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.Currency
import ru.practicum.android.diploma.core.utils.formatSalaryRange
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.presentation.common.components.salaryStrings
import ru.practicum.android.diploma.presentation.vacancy.DescriptionLine

@Composable
fun ContentBody(vacancy: Vacancy, descriptions: List<DescriptionLine>) {
    val salaryStrings = salaryStrings()
    val currencySymbol = vacancy.salary?.currency?.let { code ->
        runCatching { Currency.valueOf(code).symbol }.getOrDefault(code)
    }
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = vacancy.name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 8.dp),
        )
        Text(
            text = formatSalaryRange(
                min = vacancy.salary?.from,
                max = vacancy.salary?.to,
                formattedCurrency = vacancy.salary?.formattedCurrency ?: "",
                strings = salaryStrings
            ),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = 8.dp),
        )
        EmployerCard(vacancy)
        Spacer(modifier = Modifier.height(24.dp))
        vacancy.experience?.let {
            TextBlock(title = R.string.required_experience, text = it)
        }
        val scheduleInfo = listOfNotNull(
            vacancy.employment,
            vacancy.schedule
        )
            .joinToString(", ")
        if (scheduleInfo.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            TextBlock(text = scheduleInfo)
        }
        Spacer(modifier = Modifier.height(32.dp))
        TitleBlock(R.string.vacancy_description)
        DescriptionBlock(descriptions)
        if (vacancy.skills.isNotEmpty()) {
            SkillsBlock(vacancy.skills)
        }
        vacancy.contacts?.let { contacts ->
            ContactsBlock(contacts)
        }
    }
}
