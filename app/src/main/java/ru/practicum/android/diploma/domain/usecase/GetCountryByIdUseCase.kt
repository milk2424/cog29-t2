package ru.practicum.android.diploma.domain.usecase

import ru.practicum.android.diploma.domain.model.Area
import ru.practicum.android.diploma.domain.repository.CountryRepository

class GetCountryByIdUseCase(
    private val repository: CountryRepository
) {
    suspend operator fun invoke(countryId: String): Area? {
        return repository.getCountryById(countryId)
    }
}
