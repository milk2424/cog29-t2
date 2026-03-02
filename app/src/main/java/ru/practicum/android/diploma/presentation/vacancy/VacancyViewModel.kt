package ru.practicum.android.diploma.presentation.vacancy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.api.FavoritesInteractor
import ru.practicum.android.diploma.domain.api.utils.ApiResult
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.domain.vacancy.usecases.GetVacancyDetailUseCase
import ru.practicum.android.diploma.domain.vacancy.usecases.ShareVacancyUseCase

class VacancyViewModel(
    private val getVacancyDetailUseCase: GetVacancyDetailUseCase,
    private val favoritesInteractor: FavoritesInteractor,
    private val shareVacancyUseCase: ShareVacancyUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<VacancyScreenState>(VacancyScreenState.Loading)
    val uiState: StateFlow<VacancyScreenState> = _uiState.asStateFlow()

    private var currentVacancy: Vacancy? = null

    fun loadVacancy(vacancyId: String) {
        viewModelScope.launch {
            getVacancyDetailUseCase(vacancyId)
                .onStart {
                    _uiState.value = VacancyScreenState.Loading
                }
                .collectLatest { result ->
                    when (result) {
                        is ApiResult.Loading -> {
                            _uiState.value = VacancyScreenState.Loading
                        }

                        is ApiResult.Success -> {
                            result.data.let { vacancy ->
                                currentVacancy = vacancy
                                val isFavorite = favoritesInteractor.isFavorite(vacancy.id)
                                _uiState.value = VacancyScreenState.Content(
                                    vacancy = vacancy,
                                    isFavorite = isFavorite
                                )
                            }
                        }

                        //Выполнить проверку на код 404 (при этом удалить вакансию из БД) и присвоить стейт VacancyScreenState.NotFound
                        //else - просто присваиваем стейт VacancyScreenState.ServerError

                        else -> {
                            favoritesInteractor.remove(vacancyId)
                            _uiState.value = VacancyScreenState.NotFound
                        }
                    }
                }
        }
    }

    fun toggleFavorite() {
        viewModelScope.launch {
            currentVacancy?.let {
                if (favoritesInteractor.isFavorite(it.id)) {
                    favoritesInteractor.remove(it.id)
                } else {
                    favoritesInteractor.add(it)
                }
                currentVacancy?.id?.let { vacancyId ->
                    _uiState.value = VacancyScreenState.Content(
                        vacancy = it,
                        isFavorite = favoritesInteractor.isFavorite(vacancyId)
                    )
                }
            }
        }
    }

    fun shareVacancy() {
        currentVacancy?.let { vacancy ->
            shareVacancyUseCase(vacancy)
        }
    }
}
