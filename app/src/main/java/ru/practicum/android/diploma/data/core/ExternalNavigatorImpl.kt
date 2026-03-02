package ru.practicum.android.diploma.data.core

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri
import ru.practicum.android.diploma.domain.core.repository.ExternalNavigator
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.models.VacancySalary

class ExternalNavigatorImpl(private val context: Context) : ExternalNavigator {
    override fun openBrowser(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, url.toUri()).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        context.startActivity(intent)
    }

    override fun shareVacancy(vacancy: Vacancy) {
        val shareText = formatVacancyForSharing(vacancy)
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, shareText)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(shareIntent)
    }

    private fun formatVacancyForSharing(vacancy: Vacancy): String = buildString {
        appendHeader(vacancy)
        appendSalary(vacancy.salary)
        appendIfNotBlank("📌 ", vacancy.experience)
        appendWorkInfo(vacancy)
        appendIfNotBlank("\n📋 ", vacancy.description)
        appendSkills(vacancy.skills)
        appendContacts(vacancy)
        appendIfNotBlank("\n🔗 ", vacancy.url)
    }

    private fun StringBuilder.appendHeader(vacancy: Vacancy) {
        appendLine("🔹 ${vacancy.name}")
        appendLine("🏢 ${vacancy.employer.name}")
        appendLine("📍 ${vacancy.areaName}")
    }

    private fun StringBuilder.appendSalary(salary: VacancySalary?) {
        salary?.let {
            val salaryText = when {
                it.from != null && it.to != null -> "💰 ${it.from}–${it.to} ${it.currency}"
                it.from != null -> "💰 от ${it.from} ${it.currency}"
                it.to != null -> "💰 до ${it.to} ${it.currency}"
                else -> return
            }
            appendLine(salaryText)
        }
    }

    private fun StringBuilder.appendWorkInfo(vacancy: Vacancy) {
        val workInfo = listOfNotNull(vacancy.employment, vacancy.schedule).joinToString(" · ")
        if (workInfo.isNotBlank()) {
            appendLine("⏰ $workInfo")
        }
    }

    private fun StringBuilder.appendSkills(skills: List<String>) {
        if (skills.isNotEmpty()) {
            appendLine()
            appendLine("🔧 ${skills.joinToString(" · ")}")
        }
    }

    private fun appendContacts(vacancy: Vacancy) {
        vacancy.contacts?.let {
            val contactsText = StringBuilder()
            it.phone?.let { phone ->
                contactsText.appendLine("📞 $phone")
            }
            it.email?.let { email ->
                contactsText.appendLine("✉️ $email")
            }
        }
    }

    private fun StringBuilder.appendIfNotBlank(prefix: String, value: String?) {
        if (!value.isNullOrBlank()) {
            appendLine("$prefix$value")
        }
    }
}
