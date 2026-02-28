package ru.practicum.android.diploma.presentation.vacancy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.utils.ApiResult
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.impl.FavoritesInteractorImpl
import ru.practicum.android.diploma.domain.vacancy.usecases.GetVacancyDetailUseCase

class VacancyViewModel(
    private val getVacancyDetailUseCase: GetVacancyDetailUseCase,
    private val favoritesInteractor: FavoritesInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow<VacancyUiState>(VacancyUiState.Loading)
    val uiState: StateFlow<VacancyUiState> = _uiState.asStateFlow()

    private var currentVacancy: Vacancy? = null

    fun loadVacancy(vacancyId: String) {
        viewModelScope.launch {
            getVacancyDetailUseCase(vacancyId)
                .onStart {
                    _uiState.value = VacancyUiState.Loading
                }
                .collectLatest { result ->
                    when (result) {
                        is ApiResult.Loading -> {
                            _uiState.value = VacancyUiState.Loading
                        }

                        is ApiResult.Success -> {
                            result.data.let { vacancy ->
                                currentVacancy = vacancy
                                val isFavorite = favoritesInteractor.isFavorite(vacancy.id)
                                _uiState.value = VacancyUiState.Content(
                                    vacancy = vacancy,
                                    isFavorite = isFavorite
                                )
                            }
                        }

                        else -> {
                            favoritesInteractor.remove(vacancy.id)
                            _uiState.value = VacancyUiState.Error
                        }
                    }
                }
        }
    }

    fun toggleFavorite() {
        favoritesInteractor.add(currentVacancy)
        currentVacancy?.let {
            _uiState.value = VacancyUiState.Content(
                vacancy = it,
                isFavorite = favoritesInteractor.isFavorite(currentVacancy?.id)
            )
        }
    }

    fun shareVacancy() {
        currentVacancy?.let { getVacancyDetailUseCase.shareVacancy(it.getShareText()) }
    }

    private fun Vacancy.getShareText(): String {
        return buildString {
            appendLine("$🔹 ВАКАНСИЯ: $name")
            appendLine("═══════════════════════════")
            appendLine()

            appendLine("🏢 Компания: ${employer.name}")
            if (!employer.logo.isNullOrEmpty()) {
                appendLine("🖼 Логотип: ${employer.logo}")
            }
            appendLine()

            appendLine("📍 Город: $areaName")
            appendLine()

            salary?.let {
                append("💰 Зарплата: ")
                when {
                    it.from != null && it.to != null -> appendLine("от ${it.from} до ${it.to} ${it.currency}")
                    it.from != null -> appendLine("от ${it.from} ${it.currency}")
                    it.to != null -> appendLine("до ${it.to} ${it.currency}")
                    else -> appendLine("не указана")
                }
                appendLine()
            } ?: run {
                appendLine("💰 Зарплата: не указана")
                appendLine()
            }

            if (!experience.isNullOrBlank()) {
                appendLine("📌 Опыт работы: $experience")
                appendLine()
            }

            if (!schedule.isNullOrBlank() || !employment.isNullOrBlank()) {
                append("⏰ Режим работы: ")
                val workInfo = listOfNotNull(employment, schedule).joinToString(" · ")
                appendLine(workInfo)
                appendLine()
            }

            if (description.isNotBlank()) {
                appendLine("📋 Описание:")
                appendLine(description)
                appendLine()
            }

            if (skills.isNotEmpty()) {
                appendLine("🔧 Ключевые навыки:")
                skills.forEachIndexed { index, skill ->
                    appendLine("  ${index + 1}. $skill")
                }
                appendLine()
            }

            contacts?.let {
                var hasContacts = false
                val contactsText = StringBuilder()

                it.phone?.let { phone ->
                    contactsText.appendLine("📞 Телефон: $phone")
                    hasContacts = true
                }

                it.email?.let { email ->
                    contactsText.appendLine("✉️ Email: $email")
                    hasContacts = true
                }

                if (hasContacts) {
                    appendLine("📱 Контакты:")
                    append(contactsText.toString())
                    appendLine()
                }
            }

            if (url.isNotBlank()) {
                appendLine("🔗 Ссылка на вакансию:")
                appendLine(url)
                appendLine()
            }

            appendLine("═══════════════════════════")
        }
    }
}

sealed interface VacancyUiState {
    data object Loading : VacancyUiState
    data object Error : VacancyUiState

    data class Content(
        val vacancy: Vacancy,
        val isFavorite: Boolean
    ) : VacancyUiState
}
