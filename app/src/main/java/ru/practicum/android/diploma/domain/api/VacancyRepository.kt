package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.utils.ApiResult
import ru.practicum.android.diploma.domain.models.VacanciesResult

interface VacancyRepository {
    fun searchVacancies(expression: String, page: Int): Flow<ApiResult<VacanciesResult>>
}
