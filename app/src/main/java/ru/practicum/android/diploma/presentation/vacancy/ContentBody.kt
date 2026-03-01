package ru.practicum.android.diploma.presentation.vacancy

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
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.Dimens.paddingLarge
import ru.practicum.android.diploma.core.ui.theme.Dimens.paddingSmall
import ru.practicum.android.diploma.core.ui.theme.Dimens.spacer24
import ru.practicum.android.diploma.core.ui.theme.Dimens.spacer32
import ru.practicum.android.diploma.core.ui.theme.Dimens.spacer8
import ru.practicum.android.diploma.core.utils.Currency
import ru.practicum.android.diploma.core.utils.formatSalaryRange
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.presentation.common.components.salaryStrings

@Composable
fun ContentBody(vacancy: Vacancy) {
    val salaryStrings = salaryStrings()
    val currencySymbol = vacancy.salary?.currency?.let { code ->
        runCatching { Currency.valueOf(code).symbol }.getOrDefault(code)
    }
    Column(
        modifier = Modifier
            .padding(horizontal = paddingLarge)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = vacancy.name,
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = paddingSmall),
        )
        Text(
            text = formatSalaryRange(
                min = vacancy.salary?.from,
                max = vacancy.salary?.to,
                currency = currencySymbol,
                strings = salaryStrings
            ),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(top = paddingSmall),
        )
        EmployerCard(vacancy)
        Spacer(modifier = Modifier.height(spacer24))
        vacancy.experience?.let {
            TextBlock(title = R.string.required_experience, text = it)
        }
        val scheduleInfo = listOfNotNull(
            vacancy.employment,
            vacancy.schedule
        )
            .joinToString(", ")
        if (scheduleInfo.isNotEmpty()) {
            Spacer(modifier = Modifier.height(spacer8))
            TextBlock(text = scheduleInfo)
        }
        Spacer(modifier = Modifier.height(spacer32))
        TitleBlock(R.string.vacancy_description)
        DescriptionBlock(vacancy.description)
        if (vacancy.skills.isNotEmpty()) {
            SkillsBlock(vacancy.skills)
        }
        vacancy.contacts?.let { contacts ->
            ContactsBlock(contacts)
        }
    }
}
