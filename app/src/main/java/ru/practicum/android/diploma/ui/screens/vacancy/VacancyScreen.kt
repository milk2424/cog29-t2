package ru.practicum.android.diploma.ui.screens.vacancy

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.preview.PreviewData
import ru.practicum.android.diploma.ui.theme.DiplomaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VacancyScreen(
    state: VacancyScreenState,
    onBackClick: () -> Unit,
    onShareClick: () -> Unit,
    onFavoriteClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Text(
                            stringResource(id = R.string.to),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.background
                        )
                    }
                },
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(24.dp))

        }
    }
}


@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showSystemUi = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PreviewVacancyScreen() {
    DiplomaTheme {
        VacancyScreen(
            state = VacancyScreenState.Content(PreviewData.previewVacancy),
            onBackClick = {},
            onShareClick = {},
            onFavoriteClick = {},
        )
    }
}
