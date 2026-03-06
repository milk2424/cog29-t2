package ru.practicum.android.diploma.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.VacanciesResult
import ru.practicum.android.diploma.domain.utils.ApiResult

interface SearchInteractor {
    fun searchVacancies(expression: String, page: Int = 0): Flow<ApiResult<VacanciesResult>>
}
