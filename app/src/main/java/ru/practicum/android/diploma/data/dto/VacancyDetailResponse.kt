package ru.practicum.android.diploma.data.dto

class VacancyDetailResponse(
    val vacancy: VacancyDetailDto? = null
) : Response()

data class Salary(
    val from: Int?,
    val to: Int?,
    val currency: String?
)

data class Address(
    val city: String,
    val street: String,
    val building: String,
    val fullAddress: String,
)

data class Experience(
    val id: String,
    val name: String
)

data class Schedule(
    val id: String,
    val name: String,
)

data class Employment(
    val id: String,
    val name: String,
)

data class Contacts(
    val id: String,
    val name: String,
    val email: String,
    val phone: List<String>,
)

data class Employer(
    val id: String,
    val name: String,
    val logo: String,
)
