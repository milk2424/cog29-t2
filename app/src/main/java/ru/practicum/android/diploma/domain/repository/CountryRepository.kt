package ru.practicum.android.diploma.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.Area
import ru.practicum.android.diploma.domain.utils.ApiResult

interface CountryRepository {

    fun loadCountries(): Flow<ApiResult<List<Area>>>

}
