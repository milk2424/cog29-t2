package ru.practicum.android.diploma.domain.api

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.models.VacanciesResult
import ru.practicum.android.diploma.domain.api.utils.ApiResult

interface SearchInteractor {
    fun searchVacancies(expression: String, page: Int = 0): Flow<ApiResult<VacanciesResult>>
}
