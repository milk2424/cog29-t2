package ru.practicum.android.diploma.data.dto.industries

import ru.practicum.android.diploma.data.dto.Response

class IndustriesResponse(
    val industries: List<FilterIndustryDto> = emptyList()
) : Response()
