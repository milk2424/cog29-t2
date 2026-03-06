package ru.practicum.android.diploma.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    suspend fun addVacancy(vacancy: VacancyEntity)

    @Query("DELETE FROM vacancy WHERE id = :vacancyId")
    suspend fun deleteVacancy(vacancyId: String)

    @Query("SELECT * FROM vacancy WHERE id = :id")
    suspend fun getVacancy(id: String): VacancyEntity?

    @Query("SELECT * FROM vacancy")
    fun getAllVacancies(): Flow<List<VacancyEntity>>
}
