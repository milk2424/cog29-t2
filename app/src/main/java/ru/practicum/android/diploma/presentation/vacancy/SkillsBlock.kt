package ru.practicum.android.diploma.presentation.vacancy

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.Dimens.paddingSmall
import ru.practicum.android.diploma.core.ui.theme.Dimens.spacer16
import ru.practicum.android.diploma.core.ui.theme.Dimens.spacer24

@Composable
fun SkillsBlock(skills: List<String>) {
    Spacer(modifier = Modifier.height(spacer24))
    TitleBlock(R.string.key_skills)
    Spacer(modifier = Modifier.height(spacer16))
    skills.forEach { skill ->
        Row(modifier = Modifier.padding(start = paddingSmall)) {
            Text(
                text = "·  ",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground,
            )
            TextBlock(text = skill)
        }
    }
}
