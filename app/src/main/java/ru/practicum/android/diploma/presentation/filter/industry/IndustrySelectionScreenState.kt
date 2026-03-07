package ru.practicum.android.diploma.presentation.filter.industry

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.PersistentList
import ru.practicum.android.diploma.domain.model.Industry

@Immutable
sealed interface IndustrySelectionScreenState {
    data object Loading : IndustrySelectionScreenState
    data object Error : IndustrySelectionScreenState
    data class Success(
        val industries: PersistentList<Industry>,
        val selectedIndustryId: String? = null
    ) : IndustrySelectionScreenState
}
