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
import ru.practicum.android.diploma.domain.impl.FavoritesInteractor
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

    fun Vacancy.getShareText(): String {
        return buildString {
            appendLine("🔹 $name")
            appendLine("🏢 ${employer.name}")
            appendLine("📍 $areaName")
            salary?.let {
                when {
                    it.from != null && it.to != null -> appendLine("💰 ${it.from}–${it.to} ${it.currency}")
                    it.from != null -> appendLine("💰 от ${it.from} ${it.currency}")
                    it.to != null -> appendLine("💰 до ${it.to} ${it.currency}")
                }
            }
            if (!experience.isNullOrBlank()) appendLine("📌 $experience")
            val workInfo = listOfNotNull(employment, schedule).joinToString(" · ")
            if (workInfo.isNotBlank()) appendLine("⏰ $workInfo")
            if (description.isNotBlank()) {
                appendLine()
                appendLine("📋 $description")
            }
            if (skills.isNotEmpty()) {
                appendLine()
                appendLine("🔧 ${skills.joinToString(" · ")}")
            }
            contacts?.let {
                val contactsText = StringBuilder()
                it.phone?.let { phone ->
                    contactsText.appendLine("📞 $phone")
                }
                it.email?.let { email ->
                    contactsText.appendLine("✉️ $email")
                }
            }
            if (url.isNotBlank()) {
                appendLine()
                appendLine("🔗 $url")
            }
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
