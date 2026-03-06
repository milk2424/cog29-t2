package ru.practicum.android.diploma.presentation.team

import AppScaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.presentation.team.components.DeveloperListItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(viewModel: TeamViewModel = koinViewModel()) {
    val listOfDevelopers by viewModel.developers.collectAsStateWithLifecycle()

    AppScaffold(
        title = R.string.tab_team
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            item {
                Text(
                    modifier = Modifier.padding(vertical = 16.dp),
                    text = stringResource(R.string.developers_of_this_app),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            items(listOfDevelopers) { developer ->
                DeveloperListItem(
                    developer = developer,
                    onIconClicked = { url ->
                        viewModel.onGithubIconClicked(url)
                    }
                )
            }
        }
    }
}
