package ru.practicum.android.diploma.domain.model

import ru.practicum.android.diploma.core.utils.Currency

data class VacancySalary(
    val from: Int?,
    val to: Int?,
    val currency: String?,
    val formattedCurrency: String
) {
    companion object {
        fun from(currencyCode: String?): String {
            return currencyCode?.let { code ->
                runCatching { Currency.valueOf(code).symbol }.getOrDefault(code)
            } ?: ""
        }
    }
}
