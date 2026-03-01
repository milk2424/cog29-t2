package ru.practicum.android.diploma.domain.interactor

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.VacanciesResult
import ru.practicum.android.diploma.domain.repository.VacancyRepository
import ru.practicum.android.diploma.domain.utils.ApiResult

class SearchInteractorImpl(private val vacancyRepository: VacancyRepository) : SearchInteractor {
    override fun searchVacancies(expression: String, page: Int): Flow<ApiResult<VacanciesResult>> {
        return vacancyRepository.searchVacancies(expression, page)
    }
}
