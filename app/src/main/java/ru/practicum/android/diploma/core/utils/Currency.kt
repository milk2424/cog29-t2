package ru.practicum.android.diploma.core.utils

/**
 * Валюты поддерживаемые в приложении, согласно ТЗ
 */

enum class Currency(val symbol: String) {
    RUB("₽"),
    RUR("₽"),
    USD("$"),
    EUR("€"),
    BYR("Br"),
    KZT("₸"),
    AZN("₼"),
    GEL("₾"),
    UAH("₴"),
    UZS("so'm"),
    KGT("cом"),

    // Дополнительно встречаются в ответах от сервера
    GBP("£"), // Британский фунт
    SEK("SEK"), // Шведская крона
    SGD("S$"), // Сингапурский доллар
    NZD("NZ$"), // Новозеландский доллар
    HKD("HK$"), // Гонконгский доллар
    AUD("A$") // Австралийский доллар
}
