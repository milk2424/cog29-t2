package ru.practicum.android.diploma.domain.interactor

import android.util.Log
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.FilterSettings
import ru.practicum.android.diploma.domain.model.VacanciesResult
import ru.practicum.android.diploma.domain.model.VacancySearchParams
import ru.practicum.android.diploma.domain.repository.VacancyRepository
import ru.practicum.android.diploma.domain.utils.ApiResult

class SearchInteractorImpl(
    private val vacancyRepository: VacancyRepository
) : SearchInteractor {
    override fun searchVacancies(
        expression: String,
        page: Int,
        filter: FilterSettings
    ): Flow<ApiResult<VacanciesResult>> {
        val params = VacancySearchParams(
            expression = expression,
            page = page,
            salary = filter.salary,
            onlyWithSalary = filter.hideWithoutSalary,
            industry = filter.industryId,
            area = filter.regionId ?: filter.countryId
        )
        Log.d(
            "2222222222222222222222222222",
            "Search params: expression=$expression, industr=${params.industry}, salary=${params.salary}"
        )
        return vacancyRepository.searchVacancies(params)
    }
}
