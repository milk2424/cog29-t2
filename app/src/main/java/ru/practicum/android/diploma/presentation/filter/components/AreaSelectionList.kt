package ru.practicum.android.diploma.presentation.filter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.collections.immutable.ImmutableList
import ru.practicum.android.diploma.domain.model.Area

@Composable
fun AreaSelectionList(paddingValues: PaddingValues, items: ImmutableList<Area>, onItemClicked: (Area) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = MaterialTheme.colorScheme.background)
    ) {
        items(
            items = items,
            key = { it.id }
        ) { country ->
            AreaSelectionItem(country.name) {
                // Передача данных назад в Фильтр
                onItemClicked(country)
            }
        }
    }
}
