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
import ru.practicum.android.diploma.domain.impl.FavoritesInteractor
import ru.practicum.android.diploma.domain.models.Vacancy
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
        currentVacancy?.let { getVacancyDetailUseCase.shareVacancy(getShareText()) }
    }

    private fun getShareText(): String = buildString {
        currentVacancy?.let {
            appendHeader()
            appendSalary()
            appendIfNotBlank("📌 ", it.experience)
            appendWorkInfo()
            appendIfNotBlank("\n📋 ", it.description)
            appendSkills()
            appendContacts()
            appendIfNotBlank("\n🔗 ", it.url)
        }
    }

    private fun StringBuilder.appendHeader() {
        currentVacancy?.let {
            appendLine("🔹 ${it.name}")
            appendLine("🏢 ${it.employer.name}")
            appendLine("📍 ${it.areaName}")
        }
    }

    private fun StringBuilder.appendSalary() {
        currentVacancy?.salary?.let {
            val salaryText = when {
                it.from != null && it.to != null -> "💰 ${it.from}–${it.to} ${it.currency}"
                it.from != null -> "💰 от ${it.from} ${it.currency}"
                it.to != null -> "💰 до ${it.to} ${it.currency}"
                else -> return
            }
            appendLine(salaryText)
        }
    }

    private fun StringBuilder.appendWorkInfo() {
        currentVacancy?.let {
            val workInfo = listOfNotNull(it.employment, it.schedule).joinToString(" · ")
            if (workInfo.isNotBlank()) {
                appendLine("⏰ $workInfo")
            }
        }
    }

    private fun StringBuilder.appendSkills() {
        currentVacancy?.let {
            if (it.skills.isNotEmpty()) {
                appendLine()
                appendLine("🔧 ${it.skills.joinToString(" · ")}")
            }
        }
    }

    private fun appendContacts() {
        currentVacancy?.contacts?.let {
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

sealed interface VacancyUiState {
    data object Loading : VacancyUiState
    data object Error : VacancyUiState

    data class Content(
        val vacancy: Vacancy,
        val isFavorite: Boolean
    ) : VacancyUiState
}
