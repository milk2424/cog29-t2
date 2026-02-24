package ru.practicum.android.diploma.data.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.practicum.android.diploma.data.dto.FilterAreaDto
import ru.practicum.android.diploma.data.dto.FilterIndustryDto
import ru.practicum.android.diploma.data.dto.VacanciesResponse
import ru.practicum.android.diploma.data.dto.VacancyDetailDto

interface ApiService {
    @GET("areas")
    suspend fun getAreas(): List<FilterAreaDto>

    @GET("industries")
    suspend fun getIndustries(): List<FilterIndustryDto>

    @Suppress("LongParameterList")
    @GET("vacancies")
    suspend fun getVacancies(
        @Query("area") area: Int?,
        @Query("industry") industry: Int?,
        @Query("text") text: String?,
        @Query("salary") salary: Int?,
        @Query("page") page: Int?,
        @Query("only_with_salary") onlyWithSalary: Boolean?
    ): VacanciesResponse

    @GET("vacancies/{id}")
    suspend fun getVacancyById(@Path("id") id: String): VacancyDetailDto
}
