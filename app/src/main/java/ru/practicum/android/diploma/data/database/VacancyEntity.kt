package ru.practicum.android.diploma.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vacancy")
data class VacancyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val salaryFrom: Int?,
    val salaryTo: Int?,
    val salaryCurrency: String?,
    val employerName: String,
    val employerLogo: String?,
    val areaName: String,
    val experienceName: String?,
    val scheduleName: String?,
    val employmentName: String?,
    val skills: List<String>
)
