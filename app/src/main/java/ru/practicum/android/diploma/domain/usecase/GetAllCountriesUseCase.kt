package ru.practicum.android.diploma.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.practicum.android.diploma.domain.model.Area
import ru.practicum.android.diploma.domain.repository.CountryRepository
import ru.practicum.android.diploma.domain.utils.ApiResult
import ru.practicum.android.diploma.domain.utils.sortedWithOtherAtEnd

class GetAllCountriesUseCase(private val repository: CountryRepository) {
    operator fun invoke(): Flow<ApiResult<List<Area>>> {
        return repository.loadCountries().map { result ->
            when (result) {
                is ApiResult.Success -> {
                    val sortedData = result.data.sortedWithOtherAtEnd()
                    ApiResult.Success(sortedData)
                }

                else -> result
            }
        }
    }
}
