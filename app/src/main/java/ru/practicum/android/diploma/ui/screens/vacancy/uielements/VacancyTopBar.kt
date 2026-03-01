package ru.practicum.android.diploma.ui.screens.vacancy.uielements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyTopBar(
    showActions: Boolean,
    actionsEnabled: Boolean,
    favoriteIcon: Int,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(painterResource(R.drawable.arrow_back_24px), contentDescription = "")
            }
        },
        title = {
            Text(
                stringResource(id = R.string.vacancy),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 4.dp),
            )
        },
        actions = {
            if (showActions) {
                IconButton(
                    onClick = onShareClick,
                    enabled = actionsEnabled
                ) {
                    Icon(
                        painterResource(R.drawable.sharing_24px),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = onFavoriteClick,
                    enabled = actionsEnabled
                ) {
                    Icon(
                        painterResource(favoriteIcon),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "",
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
