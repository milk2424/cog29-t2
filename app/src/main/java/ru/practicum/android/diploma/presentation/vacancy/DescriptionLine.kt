package ru.practicum.android.diploma.presentation.vacancy

sealed interface DescriptionLine {
    data class Header(val text: String) : DescriptionLine
    data class Body(val text: String) : DescriptionLine
}
