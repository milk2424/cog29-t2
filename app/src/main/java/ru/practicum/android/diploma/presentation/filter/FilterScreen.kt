package ru.practicum.android.diploma.presentation.filter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.common.components.AppScaffold
import ru.practicum.android.diploma.presentation.filter.components.FilterButton
import ru.practicum.android.diploma.presentation.filter.components.FilterListItem
import ru.practicum.android.diploma.presentation.filter.components.SalaryTextField
import ru.practicum.android.diploma.presentation.filter.components.TrailingArrow
import ru.practicum.android.diploma.presentation.filter.components.TrailingCheckbox

@Composable
fun FilterScreen(
    viewModel: FilterViewModel = koinViewModel(),
    sharedViewModel: FilterSharedViewModel,
    onStartClick: () -> Unit,
    onWorkplaceClick: () -> Unit,
    onIndustryClick: () -> Unit,
) {
    val filter by sharedViewModel.filter.collectAsStateWithLifecycle()
    val hasFilters = filter.countryName != null ||
        filter.regionName != null ||
        filter.salary != null ||
        filter.hideWithoutSalary ||
        filter.industryId != null

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
                value = listOfNotNull(
                    filter.countryName,
                    filter.regionName
                ).joinToString(", ").ifEmpty { null },
                onClick = onWorkplaceClick,
                isPlaceholder = filter.countryName == null && filter.regionName == null,
                trailing = { TrailingArrow() }
            )
            FilterListItem(
                label = R.string.industry,
                value = filter.industryName,
                onClick = onIndustryClick,
                isPlaceholder = filter.industryName == null,
                trailing = { TrailingArrow() }
            )
            Spacer(modifier = Modifier.height(24.dp))
            SalaryTextField(
                salary = filter.salary?.toString() ?: "",
                onSalaryChange = { sharedViewModel.setSalary(it.toIntOrNull()) },
                onClear = { sharedViewModel.setSalary(null) }
            )
            Spacer(modifier = Modifier.height(24.dp))
            FilterListItem(
                label = R.string.dont_show_without_salary,
                onClick = { sharedViewModel.setHideWithoutSalary(!filter.hideWithoutSalary) },
                trailing = { TrailingCheckbox(filter.hideWithoutSalary) }
            )
            Spacer(Modifier.weight(1f))
            if (hasFilters) {
                FilterButton(
                    text = R.string.apply,
                    isPrimary = true,
                    onClick = onStartClick
                )
                Spacer(Modifier.height(8.dp))
                FilterButton(
                    text = R.string.reset,
                    isPrimary = false,
                    onClick = {
                        sharedViewModel.resetFilter()
                    }
                )
            }
            Spacer(Modifier.height(24.dp))
        }
    }
}
