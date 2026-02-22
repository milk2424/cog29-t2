package ru.practicum.android.diploma.util

/**
 * Форматирует число в строку с пробелами между разраядами
 */
fun formatNumber(value: Int?): String {
    if (value == null) return ""
    return "%,d".format(value).replace(",", " ")
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
    currency: Currency?,
    strings: SalaryStrings
): String {
    val minStr = min?.let { formatNumber(it) } ?: ""
    val maxStr = max?.let { formatNumber(it) } ?: ""
    val symbol = currency?.symbol ?: ""

    return when {
        min != null && max != null -> "${strings.from} $minStr ${strings.to} $maxStr $symbol"
        min != null -> "${strings.from} $minStr $symbol"
        max != null -> "${strings.to} $maxStr $symbol"
        else -> strings.noSalary
    }
}
