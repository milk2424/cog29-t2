package ru.practicum.android.diploma.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.practicum.android.diploma.core.network.ApiService
import ru.practicum.android.diploma.core.network.NetworkCaller
import ru.practicum.android.diploma.data.mapper.toDomain
import ru.practicum.android.diploma.domain.model.Area
import ru.practicum.android.diploma.domain.repository.RegionRepository
import ru.practicum.android.diploma.domain.utils.ApiResult

class RegionRepositoryImpl(
    private val api: ApiService,
    private val networkCaller: NetworkCaller
) : RegionRepository {

    override fun loadRegions(countryId: String?): Flow<ApiResult<List<Area>>> = flow {
        emit(ApiResult.Loading)
        val response = networkCaller.safeApiCall(
            apiCall = { api.getAreas() },
            transform = { dtoList ->
                val allAreas = dtoList.toDomain()
                extractRegions(allAreas, countryId)
            }
        )
        emit(response)
    }.flowOn(Dispatchers.IO)

    private fun extractRegions(areas: List<Area>, countryId: String?): List<Area> {
        return if (countryId == null) {
            extractAllRegions(areas)
        } else {
            findCountryById(areas, countryId)?.areas ?: emptyList()
        }
    }

    private fun extractAllRegions(areas: List<Area>): List<Area> {
        val result = mutableListOf<Area>()
        fun traverse(areaList: List<Area>) {
            for (area in areaList) {
                if (area.parentId != null) {
                    result.add(area)
                }
                if (area.areas.isNotEmpty()) {
                    traverse(area.areas)
                }
            }
        }
        traverse(areas)
        return result
    }

    private fun findCountryById(areas: List<Area>, countryId: String): Area? {
        var result: Area? = null
        for (area in areas) {
            if (area.id == countryId) {
                result = area
                break
            }
            result = findCountryById(area.areas, countryId)
            if (result != null) break
        }
        return result
    }
}
