package ru.practicum.android.diploma.presentation.vacancy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.interactor.FavoritesInteractor
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.domain.usecase.GetVacancyDetailUseCase
import ru.practicum.android.diploma.domain.usecase.ShareVacancyUseCase
import ru.practicum.android.diploma.domain.utils.ApiResult

class VacancyViewModel(
    private val getVacancyDetailUseCase: GetVacancyDetailUseCase,
    private val favoritesInteractor: FavoritesInteractor,
    private val shareVacancyUseCase: ShareVacancyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<VacancyScreenState>(VacancyScreenState.Loading)
    val uiState: StateFlow<VacancyScreenState> = _uiState.asStateFlow()
    private var loadVacancyJob: Job? = null

    fun loadVacancy(vacancyId: String) {
        loadVacancyJob?.cancel()
        loadVacancyJob = viewModelScope.launch {
            _uiState.value = VacancyScreenState.Loading
            getVacancyDetailUseCase(vacancyId)
                .collectLatest { result ->
                    when (result) {
                        is ApiResult.Loading -> {}
                        is ApiResult.Success -> {
                            val isFavorite = favoritesInteractor.isFavorite(vacancyId)
                            _uiState.value = createContentState(result.data, isFavorite)
                        }

                        is ApiResult.NetworkError -> {
                            val cached = favoritesInteractor.getById(vacancyId)
                            _uiState.value = if (cached != null) {
                                createContentState(cached, true)
                            } else {
                                VacancyScreenState.ServerError
                            }
                        }

                        else -> {
                            _uiState.value = VacancyScreenState.ServerError
                        }
                    }
                }
        }
    }

    fun toggleFavorite() {
        val currentState = (_uiState.value as? VacancyScreenState.Content) ?: return
        val newIsFavorite = !currentState.isFavorite
        _uiState.value = currentState.copy(isFavorite = newIsFavorite)
        viewModelScope.launch {
            try {
                if (newIsFavorite) {
                    favoritesInteractor.add(currentState.vacancy)
                } else {
                    favoritesInteractor.remove(currentState.vacancy.id)
                }
            } catch (_: Exception) {
                _uiState.value = currentState
            }
        }
    }

    fun shareVacancy() {
        val vacancy = (_uiState.value as? VacancyScreenState.Content)?.vacancy ?: return
        shareVacancyUseCase(vacancy)
    }
}

private fun createContentState(vacancy: Vacancy, isFavorite: Boolean) =
    VacancyScreenState.Content(
        vacancy = vacancy,
        isFavorite = isFavorite,
        descriptionLines = vacancy.description.toDescriptionLines()
    )

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
