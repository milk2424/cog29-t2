package ru.practicum.android.diploma.core.utils

import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.Locale

/**
 * Форматирует число в строку с пробелами между разраядами
 */
fun formatNumber(value: Int?): String {
    if (value == null) return ""
    val symbols = DecimalFormatSymbols(Locale.US).apply {
        groupingSeparator = ' '
    }
    return DecimalFormat("#,###", symbols).format(value)
}

data class SalaryStrings(
    val from: String,
    val to: String,
    val noSalary: String
)

/**
 * @param min - минимальная зарплата
 * @param max - максимальная зарплата
 * @param currency - валюта зарплаты
 */
fun formatSalaryRange(
    min: Int?,
    max: Int?,
    formattedCurrency: String,
    strings: SalaryStrings
): String {
    val minStr = min?.let { formatNumber(it) } ?: ""
    val maxStr = max?.let { formatNumber(it) } ?: ""

    return when {
        min != null && max != null -> "${strings.from} $minStr ${strings.to} $maxStr $formattedCurrency"
        min != null -> "${strings.from} $minStr $formattedCurrency"
        max != null -> "${strings.to} $maxStr $formattedCurrency"
        else -> strings.noSalary
    }
}
