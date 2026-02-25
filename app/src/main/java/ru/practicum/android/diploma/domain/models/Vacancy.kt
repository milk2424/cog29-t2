package ru.practicum.android.diploma.domain.models

data class Vacancy(
    val id: String,
    val name: String,
    // employer
    val employerName: String,
    val employerLogo: String,
    // area
    val areaName: String,
    // salary
    val salaryFrom: Int?,
    val salaryTo: Int?,
    val salaryCurrency: String?,
    // details
    val description: String,
    val experience: String?,
    val schedule: String?,
    val employment: String?,
    val skills: List<String>,
    val url: String,
    // contacts
    val contactName: String?,
    val contactEmail: String?,
    val contactPhone: List<String>?,
)
