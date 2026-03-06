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
import ru.practicum.android.diploma.R

@Composable
fun SkillsBlock(skills: List<String>) {
    Spacer(modifier = Modifier.height(24.dp))
    TitleBlock(R.string.key_skills)
    Spacer(modifier = Modifier.height(16.dp))
    skills.forEach { skill ->
        Row(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = "·  ",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            TextBlock(text = skill)
        }
    }
}
