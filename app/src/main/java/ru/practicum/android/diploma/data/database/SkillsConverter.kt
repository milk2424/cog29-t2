package ru.practicum.android.diploma.data.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class SkillsConverter(private val gson: Gson) {
    @TypeConverter
    fun fromList(skills: List<String>): String {
        return gson.toJson(skills)
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
}
