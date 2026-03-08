package ru.practicum.android.diploma.presentation.filter.industry

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import ru.practicum.android.diploma.core.utils.debounce
import ru.practicum.android.diploma.domain.model.Industry
import ru.practicum.android.diploma.domain.usecase.GetIndustriesUseCase
import ru.practicum.android.diploma.domain.utils.ApiResult

private const val SELECTED_INDUSTRY_ID_KEY = "selected_industry_id"
private const val SELECTED_INDUSTRY_NAME_KEY = "selected_industry_name"

class IndustrySelectionViewModel(
    private val getIndustriesUseCase: GetIndustriesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<IndustrySelectionScreenState>(IndustrySelectionScreenState.Loading)
    val uiState: StateFlow<IndustrySelectionScreenState> = _uiState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private var originalIndustries: List<Industry> = emptyList()

    private var selectedIndustryId: String?
        get() = savedStateHandle[SELECTED_INDUSTRY_ID_KEY]
        set(value) {
            savedStateHandle[SELECTED_INDUSTRY_ID_KEY] = value
            updateSelectedState()
        }

    private var selectedIndustryName: String?
        get() = savedStateHandle[SELECTED_INDUSTRY_NAME_KEY]
        set(value) {
            savedStateHandle[SELECTED_INDUSTRY_NAME_KEY] = value
        }

    private val searchDebounce: (String) -> Unit = debounce(
        delayMillis = SEARCH_DELAY,
        coroutineScope = viewModelScope,
        useLastParam = true
    ) { query ->
        performLocalSearch(query)
    }

    init {
        loadIndustries()
    }

    fun loadIndustries() {
        viewModelScope.launch {
            getIndustriesUseCase()
                .catch { e ->
                    _uiState.value = IndustrySelectionScreenState.Error
                }
                .collect { result ->
                    when (result) {
                        is ApiResult.Loading -> {
                            _uiState.value = IndustrySelectionScreenState.Loading
                        }

                        is ApiResult.Success -> {
                            originalIndustries = result.data
                            updateDisplayList(originalIndustries)
                        }

                        else -> {
                            _uiState.value = IndustrySelectionScreenState.Error
                        }
                    }
                }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        searchDebounce(query)
    }

    fun onClearSearchClicked() {
        _searchQuery.value = ""
        performLocalSearch("")
    }

    private fun performLocalSearch(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalIndustries
        } else {
            originalIndustries.filter { industry ->
                industry.name.contains(query, ignoreCase = true)
            }
        }
        updateDisplayList(filteredList)
    }

    private fun updateDisplayList(industries: List<Industry>) {
        when {
            industries.isEmpty() && _searchQuery.value.isNotEmpty() -> {
                _uiState.value = IndustrySelectionScreenState.EmptySearch
            }
            else -> {
                _uiState.value = IndustrySelectionScreenState.Success(
                    industries = industries.toPersistentList(),
                    selectedIndustryId = selectedIndustryId
                )
            }
        }
    }

    private fun updateSelectedState() {
        val currentState = _uiState.value
        if (currentState is IndustrySelectionScreenState.Success) {
            _uiState.value = currentState.copy(selectedIndustryId = selectedIndustryId)
        }
    }

    fun onIndustrySelected(industryId: String) {
        selectedIndustryId = industryId
        selectedIndustryName = originalIndustries.find { it.id == industryId }?.name
    }

    fun onConfirmSelection() {
        savedStateHandle[SELECTED_INDUSTRY_ID_KEY] = selectedIndustryId
        savedStateHandle[SELECTED_INDUSTRY_NAME_KEY] = selectedIndustryName
    }

    companion object {
        private const val SEARCH_DELAY = 2000L
    }
}
