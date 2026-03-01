package ru.practicum.android.diploma.presentation.team

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.Dimens.iconMedium
import ru.practicum.android.diploma.core.ui.theme.Dimens.paddingSmall
import ru.practicum.android.diploma.domain.model.Developer

@Composable
fun DeveloperListItem(developer: Developer, onIconClicked: (String) -> Unit) {
    val githubLink = stringResource(developer.githubLinkRes)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = paddingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(developer.nameRes),
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleMedium
        )
        Icon(
            modifier = Modifier
                .size(iconMedium)
                .clip(CircleShape)
                .clickable {
                    onIconClicked(githubLink)
                },
            painter = painterResource(R.drawable.ic_github),
            contentDescription = stringResource(R.string.github_link),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}
