package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.VacanciesResult

interface SearchInteractor {
    fun searchVacancies(expression: String): Flow<Pair<VacanciesResult?, String?>>
}
