package ru.practicum.android.diploma.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.practicum.android.diploma.data.database.entity.VacancyEntity

@Dao
interface VacancyDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVacancy(vacancy: VacancyEntity)

    @Delete
    suspend fun deleteVacancy(vacancy: VacancyEntity)

    @Query("SELECT * FROM vacancy WHERE id = :id")
    suspend fun getVacancy(id: String): VacancyEntity?

    @Query("SELECT * FROM vacancy")
    fun getAllVacancies(): Flow<List<VacancyEntity>>
}
