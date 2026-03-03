package ru.practicum.android.diploma.domain.usecase

import android.content.Context
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.navigation.ExternalNavigator
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.model.VacancySalary

class ShareVacancyUseCase(
    private val externalNavigator: ExternalNavigator,
    private val context: Context
) {
    operator fun invoke(vacancy: Vacancy) {
        externalNavigator.shareVacancy(formatVacancyForSharing(vacancy))
    }

    private fun formatVacancyForSharing(vacancy: Vacancy): String = buildString {
        appendHeader(vacancy)
        appendSalary(vacancy.salary)
        appendIfNotBlank("${context.getString(R.string.experience_symbol)} ", vacancy.experience)
        appendWorkInfo(vacancy)
        appendIfNotBlank("\n${context.getString(R.string.description_symbol)} ", vacancy.description)
        appendSkills(vacancy.skills)
        appendContacts(vacancy)
        appendIfNotBlank("\n${context.getString(R.string.url_symbol)} ", vacancy.url)
    }

    private fun StringBuilder.appendHeader(vacancy: Vacancy) {
        appendLine("${context.getString(R.string.vacancy_symbol)} ${vacancy.name}")
        appendLine("${context.getString(R.string.organization_symbol)} ${vacancy.employer.name}")
        appendLine("${context.getString(R.string.location_symbol)} ${vacancy.areaName}")
    }

    private fun StringBuilder.appendSalary(salary: VacancySalary?) {
        salary?.let {
            val salaryText = when {
                it.from != null && it.to != null -> "${context.getString(R.string.salary_symbol)} ${it.from}–${it.to} ${it.currency}"
                it.from != null -> "${context.getString(R.string.salary_symbol)} от ${it.from} ${it.currency}"
                it.to != null -> "${context.getString(R.string.salary_symbol)} до ${it.to} ${it.currency}"
                else -> return
            }
            appendLine(salaryText)
        }
    }

    private fun StringBuilder.appendWorkInfo(vacancy: Vacancy) {
        val workInfo = listOfNotNull(vacancy.employment, vacancy.schedule).joinToString(" · ")
        if (workInfo.isNotBlank()) {
            appendLine("${context.getString(R.string.work_info_symbol)} $workInfo")
        }
    }

    private fun StringBuilder.appendSkills(skills: List<String>) {
        if (skills.isNotEmpty()) {
            appendLine()
            appendLine("${context.getString(R.string.skills_symbol)} ${skills.joinToString(" · ")}")
        }
    }

    private fun StringBuilder.appendContacts(vacancy: Vacancy) {
        vacancy.contacts?.let {
            it.phone?.let { phone ->
                appendLine("${context.getString(R.string.phone_symbol)} $phone")
            }
            it.email?.let { email ->
                appendLine("${context.getString(R.string.email_symbol)} $email")
            }
        }
    }

    private fun StringBuilder.appendIfNotBlank(prefix: String, value: String?) {
        if (!value.isNullOrBlank()) {
            appendLine("$prefix$value")
        }
    }
}
