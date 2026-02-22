package ru.practicum.android.diploma.util
/**
 * Форматирует число в строку с пробелами между разраядами
 */
fun formatNumber(value: Int?): String {
    if (value == null) return ""
    return "%,d".format(value).replace(",", " ")
}

/**
 * @param min - минимальная зарплата
 * @param max - максимальная зарплата
 * @param currency - валюта зарплаты
 */
fun formatSalaryRange(
    min: Int?,
    max: Int?,
    currency: Currency?,
    fromText: String,
    toText: String,
    noSalaryText: String
    ): String {
    val minStr = min?.let { formatNumber(it) } ?: ""
    val maxStr = max?.let { formatNumber(it) } ?: ""
    val symbol = currency?.symbol ?: ""

    return when {
        min != null && max != null -> "$fromText $minStr $toText $maxStr $symbol"
        min != null -> "$fromText $minStr $symbol"
        max != null -> "$toText $maxStr $symbol"
        else -> noSalaryText
    }
}
