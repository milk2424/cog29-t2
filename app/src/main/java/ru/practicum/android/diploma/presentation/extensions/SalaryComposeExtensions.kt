package ru.practicum.android.diploma.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.util.SalaryStrings

@Composable
fun salaryStrings(): SalaryStrings {
    return SalaryStrings(
        from = stringResource(R.string.from),
        to = stringResource(R.string.to),
        noSalary = stringResource(R.string.no_salary)
    )
}
