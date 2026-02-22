package ru.practicum.android.diploma.util

/**
 * Валюты поддерживаемые в приложении, согласно ТЗ
 */

enum class Currency(val symbol: String) {
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
