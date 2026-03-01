package ru.practicum.android.diploma.data.model

import com.google.gson.annotations.SerializedName

data class Contacts(
    val id: String,
    val name: String,
    val email: String,
    @SerializedName("phones")
    val phones: List<Phone>,
)
