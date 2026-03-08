package ru.practicum.android.diploma.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.Industry
import ru.practicum.android.diploma.domain.repository.IndustryRepository
import ru.practicum.android.diploma.domain.utils.ApiResult

class GetIndustriesUseCase(
    private val repository: IndustryRepository
) {
    operator fun invoke(): Flow<ApiResult<List<Industry>>> {
        return repository.getIndustries()
    }
}
