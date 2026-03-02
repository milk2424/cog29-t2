package ru.practicum.android.diploma.presentation.filter

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.core.uielements.AppScaffold
import ru.practicum.android.diploma.ui.screens.filter.uielements.FilterButton
import ru.practicum.android.diploma.ui.screens.filter.uielements.FilterListItem
import ru.practicum.android.diploma.ui.screens.filter.uielements.SalaryTextField
import ru.practicum.android.diploma.ui.screens.filter.uielements.TrailingArrow
import ru.practicum.android.diploma.ui.screens.filter.uielements.TrailingCheckbox
import ru.practicum.android.diploma.ui.theme.Dimens.spacer24
import ru.practicum.android.diploma.ui.theme.Dimens.spacer8
import ru.practicum.android.diploma.ui.theme.DiplomaTheme

@Composable
fun FilterScreen(
    onBackClick: () -> Unit,
    onWorkplaceClick: () -> Unit,
    onIndustryClick: () -> Unit,
) {
    var salary by remember { mutableStateOf("") }
    var hideWithoutSalary by remember { mutableStateOf(false) }
    val hasFilters = salary.isNotEmpty() || hideWithoutSalary
    AppScaffold(
        title = R.string.filter_settings,
        onBackClick = onBackClick,
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            FilterListItem(
                label = R.string.work_place,
                onClick = onWorkplaceClick,
                isPlaceholder = true,
                trailing = { TrailingArrow() }
            )
            FilterListItem(
                label = R.string.industry,
                onClick = onIndustryClick,
                isPlaceholder = true,
                trailing = { TrailingArrow() }
            )
            Spacer(modifier = Modifier.height(spacer24))
            SalaryTextField(
                salary = salary,
                onSalaryChange = { salary = it },
                onClear = { salary = "" }
            )
            Spacer(modifier = Modifier.height(spacer24))
            FilterListItem(
                label = R.string.dont_show_without_salary,
                onClick = { hideWithoutSalary = !hideWithoutSalary },
                trailing = { TrailingCheckbox(hideWithoutSalary) }
            )
            Spacer(Modifier.weight(1f))
            FilterButton(
                text = R.string.apply,
                isPrimary = true,
                onClick = {}
            )
            Spacer(Modifier.height(spacer8))
            FilterButton(
                text = R.string.reset,
                isPrimary = false,
                onClick = {}
            )
            Spacer(Modifier.height(spacer24))
        }
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewVacancyScreen() {
    DiplomaTheme {
        FilterScreen(
            onBackClick = {},
            onWorkplaceClick = {},
            onIndustryClick = {},
        )
    }
}
