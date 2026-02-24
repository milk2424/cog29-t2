package ru.practicum.android.diploma.data.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@ProvidedTypeConverter
class ListStringConverter(private val gson: Gson) {
    @TypeConverter
    fun fromList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toList(json: String): List<String> {
        return gson.fromJson(json, object : TypeToken<List<String>>() {}.type)
    }
}
