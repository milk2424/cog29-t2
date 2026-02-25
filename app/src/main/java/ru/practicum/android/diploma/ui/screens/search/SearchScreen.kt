package ru.practicum.android.diploma.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.placeholders.Initial
import ru.practicum.android.diploma.ui.theme.Dimens.cornerRadius
import ru.practicum.android.diploma.ui.theme.Dimens.mainPadding
import ru.practicum.android.diploma.ui.theme.Dimens.secondaryPadding

@Composable
fun SearchScreen(navController: NavController) {

    val viewModel: SearchViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val isClearButtonVisible = uiState.query.isNotEmpty()

    val clearIcon = ImageVector.vectorResource(id = R.drawable.close_24px)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(mainPadding)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                modifier = Modifier.weight(1f),
                text = "Поиск вакансий",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            IconButton(onClick = { viewModel.onFilterClicked() }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.filter_off__24px),
                    contentDescription = "Фильтр",
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        Spacer(modifier = Modifier.height(mainPadding))

        OutlinedTextField(
            value = uiState.query,
            onValueChange = { viewModel.onQueryChanged(it) },
            shape = RoundedCornerShape(cornerRadius),
            modifier = Modifier
                .padding(vertical = secondaryPadding)
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.surface,
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface
            ),
            placeholder = {
                Text(
                    text = "Введите запрос",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            },
            trailingIcon = {
                if (isClearButtonVisible) {
                    IconButton(onClick = { viewModel.onClearCLicked() }) {
                        Icon(
                            imageVector = clearIcon,
                            contentDescription = "Очистить",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                } else {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.search_24px),
                        contentDescription = "Поиск",
                        tint = MaterialTheme.colorScheme.onSurface

                    )
                }
            },
            singleLine = true
        )
        if (uiState.isInitial) {
            Initial()
        }

    }


}
