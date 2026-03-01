package ru.practicum.android.diploma.ui.screens.vacancy.uielements

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DescriptionBlock(description: String) {
    description.split("\n").filter { it.isNotBlank() }.forEach { line ->
        if (line.trimEnd().endsWith(":")) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = line,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.height(4.dp))
        } else {
            Row(modifier = Modifier.padding(start = 8.dp)) {
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
