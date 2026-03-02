package ru.practicum.android.diploma.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    version = 1,
    entities = [VacancyEntity::class],
    exportSchema = false
)
@TypeConverters(ListStringConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun vacancyDao(): VacancyDao
}
