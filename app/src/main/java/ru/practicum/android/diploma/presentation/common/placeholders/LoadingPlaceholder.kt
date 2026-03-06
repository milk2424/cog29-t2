package ru.practicum.android.diploma.presentation.common.placeholders

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.practicum.android.diploma.core.ui.theme.Dimens.paginationProgressStroke
import ru.practicum.android.diploma.core.ui.theme.Dimens.progressBarSize

@Composable
fun LoadingPlaceholder() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(progressBarSize),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = paginationProgressStroke
        )
    }
}
