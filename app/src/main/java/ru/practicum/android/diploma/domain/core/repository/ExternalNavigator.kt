package ru.practicum.android.diploma.domain.core.repository

import ru.practicum.android.diploma.domain.models.Vacancy

interface ExternalNavigator {
    fun openBrowser(url: String)
    fun shareVacancy(vacancy: Vacancy)
}
