package ru.practicum.android.diploma.data.database.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.practicum.android.diploma.data.model.Address
import ru.practicum.android.diploma.data.model.Contacts
import ru.practicum.android.diploma.data.model.Employer
import ru.practicum.android.diploma.data.model.Employment
import ru.practicum.android.diploma.data.model.Experience
import ru.practicum.android.diploma.data.model.Salary
import ru.practicum.android.diploma.data.model.Schedule

@Entity(tableName = "vacancy")
data class VacancyEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    @Embedded(prefix = "salary_") val salary: Salary?,
    @Embedded(prefix = "address_") val address: Address?,
    @Embedded(prefix = "experience_") val experience: Experience?,
    @Embedded(prefix = "schedule_") val schedule: Schedule?,
    @Embedded(prefix = "employment_") val employment: Employment?,
    @Embedded(prefix = "contacts_") val contacts: Contacts?,
    @Embedded(prefix = "employer_") val employer: Employer,
    val areaName: String,
    val skills: List<String>,
    val industryName: String?
)
