package ru.practicum.android.diploma.ui.screens.vacancy.uielements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.practicum.android.diploma.ui.theme.Dimens.paddingSmall
import ru.practicum.android.diploma.ui.theme.Dimens.spacer16
import ru.practicum.android.diploma.ui.theme.Dimens.spacer4

@Composable
fun DescriptionBlock(description: String) {
    description.split("\n").filter { it.isNotBlank() }.forEach { line ->
        if (line.trimEnd().endsWith(":")) {
            Spacer(modifier = Modifier.height(spacer16))
            Text(
                text = line,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(spacer4))
        } else {
            Row(modifier = Modifier.padding(start = paddingSmall)) {
                Text(
                    text = "·  ",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                    text = line,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground,
                )
            }
        }
    }
}
