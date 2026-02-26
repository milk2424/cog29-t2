package ru.practicum.android.diploma.presentation.team

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.domain.core.usecase.OpenInBrowserUseCase
import ru.practicum.android.diploma.domain.team.model.Developer
import ru.practicum.android.diploma.domain.team.usecase.LoadDevelopersTeamUseCase

class TeamViewModel(
    private val loadDevelopersUseCase: LoadDevelopersTeamUseCase,
    private val openInBrowserUseCase: OpenInBrowserUseCase
) : ViewModel() {

    private val _developers = MutableStateFlow<List<Developer>>(emptyList())
    val developers = _developers.asStateFlow()

    init {
        loadTeam()
    }

    private fun loadTeam() {
        viewModelScope.launch {
            loadDevelopersUseCase().collect { developers ->
                _developers.value = developers
            }
        }
    }

    fun onGithubIconClicked(url: String) {
        openInBrowserUseCase(url)
    }
}
