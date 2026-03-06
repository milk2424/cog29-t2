package ru.practicum.android.diploma.presentation.vacancy.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.presentation.vacancy.DescriptionLine

@Composable
fun DescriptionBlock(descriptions: List<DescriptionLine>) {
    descriptions.forEach { description ->
        when (description) {
            is DescriptionLine.Header -> {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = description.text,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                )
                Spacer(modifier = Modifier.height(4.dp))
            }

            is DescriptionLine.Body -> {
                Row(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        text = "·  ",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                    Text(
                        text = description.text,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onBackground,
                    )
                }
            }
        }
    }
}
