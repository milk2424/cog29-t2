package ru.practicum.android.diploma.data.dto.areas

import ru.practicum.android.diploma.data.dto.Response

class AreasResponse(
    val areas: List<FilterAreaDto> = emptyList()
) : Response()
