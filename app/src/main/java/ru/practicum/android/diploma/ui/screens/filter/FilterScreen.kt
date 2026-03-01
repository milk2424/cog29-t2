package ru.practicum.android.diploma.ui.screens.filter

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R

import ru.practicum.android.diploma.ui.core.uielements.AppScaffold
import ru.practicum.android.diploma.ui.theme.DiplomaTheme

@Composable
fun FilterScreen(
    onBackClick: () -> Unit,
    onWorkplaceClick: () -> Unit,
    onIndustryClick: () -> Unit,
) {
    AppScaffold(
        title = R.string.filter_settings,
        onBackClick = onBackClick,
    ) { paddingValues ->
        Text(
            text = "тест",
            modifier = Modifier.padding(paddingValues),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, widthDp = 360, heightDp = 1400)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewVacancyScreen() {
    DiplomaTheme {
        FilterScreen(
            onBackClick = {},
            onWorkplaceClick = {},
            onIndustryClick = {},
        )
    }
}
