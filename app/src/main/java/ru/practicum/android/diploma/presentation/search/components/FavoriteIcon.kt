package ru.practicum.android.diploma.presentation.search.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.practicum.android.diploma.R

@Composable
fun FavoriteIcon(
    isFavorite: Boolean
) {
    var iconRes = R.drawable.favorites_off__24px
    var tint = MaterialTheme.colorScheme.onBackground
    if (isFavorite) {
        iconRes = R.drawable.favorites_on__24px
        tint = MaterialTheme.colorScheme.error
    }

    Icon(
        painter = painterResource(iconRes),
        contentDescription = null,
        tint = tint
    )
}
