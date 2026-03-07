package ru.practicum.android.diploma.presentation.vacancy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.network.NetworkChecker
import ru.practicum.android.diploma.domain.interactor.FavoritesInteractor
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.usecase.GetVacancyDetailUseCase
import ru.practicum.android.diploma.domain.usecase.ShareVacancyUseCase
import ru.practicum.android.diploma.domain.utils.ApiResult

class VacancyViewModel(
    private val getVacancyDetailUseCase: GetVacancyDetailUseCase,
    private val favoritesInteractor: FavoritesInteractor,
    private val shareVacancyUseCase: ShareVacancyUseCase,
    private val networkChecker: NetworkChecker,
) : ViewModel() {

    private val _uiState = MutableStateFlow<VacancyScreenState>(VacancyScreenState.Loading)
    val uiState: StateFlow<VacancyScreenState> = _uiState.asStateFlow()

    private var currentVacancy: Vacancy? = null

    fun loadVacancy(vacancyId: String) {
        viewModelScope.launch {
            _uiState.value = VacancyScreenState.Loading
            if (networkChecker.isNetworkAvailable()) {
                getVacancyDetailUseCase(vacancyId)
                    .collectLatest { result ->
                        when (result) {
                            is ApiResult.Success -> {
                                currentVacancy = result.data
                                val isFavorite = favoritesInteractor.isFavorite(vacancyId)
                                _uiState.value = VacancyScreenState.Content(
                                    vacancy = result.data,
                                    isFavorite = isFavorite,
                                    descriptionLines = result.data.description.toDescriptionLines()
                                )
                            }

                            else -> {
                                _uiState.value = VacancyScreenState.ServerError
                            }
                        }
                    }
            } else {
                val cached = favoritesInteractor.getById(vacancyId)
                if (cached != null) {
                    currentVacancy = cached
                    _uiState.value = VacancyScreenState.Content(
                        vacancy = cached,
                        isFavorite = true,
                        descriptionLines = cached.description.toDescriptionLines()
                    )
                } else {
                    _uiState.value = VacancyScreenState.ServerError
                }
            }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            currentVacancy?.let { vacancy ->
                if (favoritesInteractor.isFavorite(vacancy.id)) {
                    favoritesInteractor.remove(vacancy.id)
                } else {
                    favoritesInteractor.add(vacancy)
                }
                _uiState.value = VacancyScreenState.Content(
                    vacancy = vacancy,
                    isFavorite = favoritesInteractor.isFavorite(vacancy.id),
                    descriptionLines = vacancy.description.toDescriptionLines()
                )
            }
        }
    }

    fun shareVacancy() {
        currentVacancy?.let { vacancy ->
            shareVacancyUseCase(vacancy)
        }
    }
}

private fun String.toDescriptionLines(): List<DescriptionLine> =
    split("\n")
        .filter { it.isNotBlank() }
        .map { line ->
            if (line.trimEnd().endsWith(":")) {
                DescriptionLine.Header(line)
            } else {
                DescriptionLine.Body(line)
            }
        }
