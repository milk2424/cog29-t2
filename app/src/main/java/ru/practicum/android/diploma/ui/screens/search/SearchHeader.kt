package ru.practicum.android.diploma.ui.screens.search

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.Dimens.mainPadding

@Composable
fun SearchHeader(
    onFilterClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = stringResource(id = R.string.search_title),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        IconButton(onClick = onFilterClick) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.filter_off__24px),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }

    Spacer(modifier = Modifier.height(mainPadding))
}
