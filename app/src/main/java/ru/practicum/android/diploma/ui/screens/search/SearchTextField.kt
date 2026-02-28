package ru.practicum.android.diploma.ui.screens.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.Dimens.iconCorners
import ru.practicum.android.diploma.ui.theme.Dimens.paddingLarge
import ru.practicum.android.diploma.ui.theme.Dimens.paddingSmall

@Composable
fun SearchTextField(
    query: String,
    onQueryChanged: (String) -> Unit,
    onClearClicked: () -> Unit
) {
    val isClearButtonVisible = query.isNotEmpty()
    val clearIcon = ImageVector.vectorResource(id = R.drawable.close_24px)

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChanged,
        shape = RoundedCornerShape(iconCorners),
        modifier = Modifier
            .padding(vertical = paddingSmall, horizontal = paddingLarge)
            .fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor = MaterialTheme.colorScheme.surface,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface
        ),
        placeholder = {
            Text(
                text = stringResource(R.string.search_placeholder),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.secondary
            )
        },
        trailingIcon = {
            if (isClearButtonVisible) {
                IconButton(onClick = onClearClicked) {
                    Icon(
                        imageVector = clearIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            } else {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.search_24px),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        singleLine = true
    )
}
