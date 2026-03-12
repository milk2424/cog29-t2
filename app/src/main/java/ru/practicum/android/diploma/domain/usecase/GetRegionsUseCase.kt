package ru.practicum.android.diploma.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.domain.model.Area
import ru.practicum.android.diploma.domain.repository.RegionRepository
import ru.practicum.android.diploma.domain.utils.ApiResult

class GetRegionsUseCase(
    private val repository: RegionRepository
) {
    operator fun invoke(countryId: String?): Flow<ApiResult<List<Area>>> {
        return repository.loadRegions(countryId).map { result ->
            when (result) {
                is ApiResult.Success -> {
                    val sortedRegions = result.data.sortedBy { it.name }
                    ApiResult.Success(sortedRegions)
                }

                else -> result
            }
        }
    }
}
