package ru.practicum.android.diploma.core.navigation

import ru.practicum.android.diploma.domain.model.Vacancy

interface ExternalNavigator {
    fun openBrowser(url: String)
    fun shareVacancy(vacancy: Vacancy)
}
