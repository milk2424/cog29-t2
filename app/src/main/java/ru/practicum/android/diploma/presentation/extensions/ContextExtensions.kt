package ru.practicum.android.diploma.presentation.extensions

import android.content.Context
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.util.SalaryStrings

fun Context.salaryStrings(): SalaryStrings {
    return SalaryStrings(
        from = getString(R.string.from),
        to = getString(R.string.to),
        noSalary = getString(R.string.no_salary)
    )
}
