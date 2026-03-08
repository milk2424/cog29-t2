package ru.practicum.android.diploma.presentation.filter

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.DiplomaTheme
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.filter.components.FilterButton
import ru.practicum.android.diploma.presentation.filter.components.FilterListItem
import ru.practicum.android.diploma.presentation.filter.components.SalaryTextField
import ru.practicum.android.diploma.presentation.filter.components.TrailingArrow
import ru.practicum.android.diploma.presentation.filter.components.TrailingCheckbox

@Composable
fun FilterScreen(
    onStartClick: () -> Unit,
    onWorkplaceClick: () -> Unit,
    onIndustryClick: () -> Unit,
) {
    var salary by remember { mutableStateOf("") }
    var hideWithoutSalary by remember { mutableStateOf(false) }
    val hasFilters = salary.isNotEmpty() || hideWithoutSalary
    val focusManager = LocalFocusManager.current
    AppScaffold(
        title = R.string.filter_settings,
        showStartButton = true,
        onStartClick = onStartClick
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { focusManager.clearFocus() }
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
            Spacer(modifier = Modifier.height(24.dp))
            SalaryTextField(
                salary = salary,
                onSalaryChange = { salary = it },
                onClear = { salary = "" }
            )
            Spacer(modifier = Modifier.height(24.dp))
            FilterListItem(
                label = R.string.dont_show_without_salary,
                onClick = { hideWithoutSalary = !hideWithoutSalary },
                trailing = { TrailingCheckbox(hideWithoutSalary) }
            )
            Spacer(Modifier.weight(1f))
            if (hasFilters) {
                FilterButton(
                    text = R.string.apply,
                    isPrimary = true,
                    onClick = {}
                )
                Spacer(Modifier.height(8.dp))
                FilterButton(
                    text = R.string.reset,
                    isPrimary = false,
                    onClick = {
                        salary = ""
                        hideWithoutSalary = false
                    }
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewVacancyScreen() {
    DiplomaTheme {
        FilterScreen(
            onStartClick = {},
            onWorkplaceClick = {},
            onIndustryClick = {},
        )
    }
}
