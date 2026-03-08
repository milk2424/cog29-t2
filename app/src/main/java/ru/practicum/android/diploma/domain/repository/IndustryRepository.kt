package ru.practicum.android.diploma.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.domain.model.Industry
import ru.practicum.android.diploma.domain.utils.ApiResult

interface IndustryRepository {
    fun getIndustries(): Flow<ApiResult<List<Industry>>>
}
