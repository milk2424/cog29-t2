package ru.practicum.android.diploma.ui.screens.vacancy.uielements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.Dimens.spacer4
import ru.practicum.android.diploma.ui.theme.Dimens.spacer6

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
                Icon(
                    painter = painterResource(id = R.drawable.arrow_back_24px),
                    contentDescription = ""
                )
            }
        },
        title = {
            Spacer(Modifier.height(spacer4))
            TitleBlock(R.string.vacancy)
        },
        actions = {
            if (showActions) {
                IconButton(
                    onClick = onShareClick,
                    enabled = actionsEnabled
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.sharing_24px),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = ""
                    )
                }
                IconButton(
                    onClick = onFavoriteClick,
                    enabled = actionsEnabled
                ) {
                    Icon(
                        painter = painterResource(id = favoriteIcon),
                        tint = MaterialTheme.colorScheme.onBackground,
                        contentDescription = "",
                    )
                }
                Spacer(modifier = Modifier.width(spacer6))
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    )
}
