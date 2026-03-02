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
    KGT("cом")
}
