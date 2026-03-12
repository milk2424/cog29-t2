package ru.practicum.android.diploma.core.logging

import android.util.Log

class AppLoggerImpl : AppLogger {
    override fun error(tag: String, message: String, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }
}
