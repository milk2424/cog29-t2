package ru.practicum.android.diploma.core.navigation

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

class ExternalNavigatorImpl(private val context: Context) : ExternalNavigator {
    override fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }
}
