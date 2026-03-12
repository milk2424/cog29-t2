package ru.practicum.android.diploma.presentation.filter.components

import android.content.res.Configuration
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.DiplomaTheme

@Composable
fun FilterButton(
    @StringRes text: Int,
    isPrimary: Boolean,
    onClick: () -> Unit,
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 17.dp)
            .background(
                color = if (isPrimary) {
                    MaterialTheme.colorScheme.primary
                } else {
                    Color.Transparent
                },
                shape = MaterialTheme.shapes.medium
            )
            .padding(vertical = 20.dp)
            .clickable(onClick = onClick)
    ) {
        Text(
            text = stringResource(text),
            style = MaterialTheme.typography.titleMedium,
            color = if (isPrimary) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MaterialTheme.colorScheme.error
            }
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewFilterButton1() {
    DiplomaTheme {
        FilterButton(
            text = R.string.apply,
            isPrimary = true,
            onClick = {},
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewFilterButton2() {
    DiplomaTheme {
        FilterButton(
            text = R.string.reset,
            isPrimary = false,
            onClick = {},
        )
    }
}
