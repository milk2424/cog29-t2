package ru.practicum.android.diploma.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.VacanciesResult
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.utils.ApiResult

interface VacancyRepository {
    fun searchVacancies(
        expression: String,
        page: Int,
        salary: Int?,
        onlyWithSalary: Boolean?,
        industry: Int?,
        area: Int?
    ): Flow<ApiResult<VacanciesResult>>

    fun getVacancyDetail(id: String): Flow<ApiResult<Vacancy>>
}
