package ru.practicum.android.diploma.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.practicum.android.diploma.data.database.converter.ListStringConverter
import ru.practicum.android.diploma.data.database.entity.VacancyEntity

@Database(
    version = 1,
    entities = [VacancyEntity::class],
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao
}
