package ru.practicum.android.diploma.core.logging

interface AppLogger {
    fun error(tag: String, message: String, throwable: Throwable?)
}
