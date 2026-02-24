package ru.practicum.android.diploma.data.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SkillsConverter {
    @TypeConverter
    fun fromList(skills: List<String>): String {
        return Gson().toJson(skills)
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        return Gson().fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
}
