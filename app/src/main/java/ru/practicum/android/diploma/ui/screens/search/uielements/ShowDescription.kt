package ru.practicum.android.diploma.ui.screens.search.uielements

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.practicum.android.diploma.ui.theme.Dimens.paddingMedium
import ru.practicum.android.diploma.ui.theme.Dimens.paddingXSmall

@Composable
fun ShowDescription(
    message: String
) {
    Box(
        modifier = Modifier
            .padding(paddingXSmall)
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(paddingMedium)
            )
    ) {
        Text(
            modifier = Modifier
                .padding(horizontal = paddingMedium, vertical = paddingXSmall),
            text = message,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
