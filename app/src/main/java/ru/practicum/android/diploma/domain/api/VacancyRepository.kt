package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.VacanciesResult
import ru.practicum.android.diploma.util.Resource

interface VacancyRepository {
    fun searchVacancies(expression: String): Flow<Resource<VacanciesResult>>
}
