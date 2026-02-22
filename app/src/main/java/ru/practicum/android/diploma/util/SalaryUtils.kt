package ru.practicum.android.diploma.util
/**
 * Валюты поддерживаемые в приложении, согласно ТЗ
 */
enum class Currency(val symbol: String){
    RUR("₽"),
    USD("$"),
    EUR("€"),
    BYR("Br"),
    KZT("₸"),
    AZN("₼"),
    GEL("₾"),
    UAH("₴"),
    UZS("so'm"),
    KGT("com")
}

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
fun formatSalaryRange(min: Int?, max: Int?, currency: Currency?): String {
    val minStr = min?.let{ formatNumber(it)} ?: ""
    val maxStr = max?.let{ formatNumber(it)} ?: ""
    val symbol = currency?.symbol ?: ""

    return when {
        min != null && max != null -> "От $minStr до $maxStr $symbol"
        min != null -> "От $minStr $symbol"
        max != null -> "До $maxStr $symbol"
        else -> "Зарплата не указана"

    }


}
