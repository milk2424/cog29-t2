package ru.practicum.android.diploma.domain.impl

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.api.SearchInteractor
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.domain.models.VacanciesResult
import ru.practicum.android.diploma.domain.api.utils.ApiResult

class SearchInteractorImpl(private val vacancyRepository: VacancyRepository) : SearchInteractor {
    override fun searchVacancies(expression: String, page: Int): Flow<ApiResult<VacanciesResult>> {
        return vacancyRepository.searchVacancies(expression, page)
    }
}
