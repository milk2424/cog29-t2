package ru.practicum.android.diploma.presentation.filter.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.DiplomaTheme

@Composable
fun FilterListItem(
    label: Int? = null,
    value: String? = null,
    onClick: () -> Unit,
    isPlaceholder: Boolean = false,
    trailing: @Composable () -> Unit,
) {
    val color = if (isPlaceholder) {
        MaterialTheme.colorScheme.onSurfaceVariant
    } else {
        MaterialTheme.colorScheme.onBackground
    }
    val labelStyle = if (value != null) {
        MaterialTheme.typography.bodyMedium
    } else {
        MaterialTheme.typography.bodyLarge
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.weight(1f)) {
            if (label != null) {
                Text(
                    text = stringResource(id = label),
                    style = labelStyle,
                    color = color
                )
            }
            if (value != null) {
                Text(
                    text = value,
                    style = MaterialTheme.typography.bodyLarge,
                    color = color,
                    modifier = Modifier.padding(end = 16.dp)
                )
            }
        }
        trailing()
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewFilterListItemPlaceHolder() {
    DiplomaTheme {
        FilterListItem(
            label = R.string.country,
            isPlaceholder = true,
            onClick = {},
            trailing = { TrailingArrow() }
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewFilterListItem2() {
    DiplomaTheme {
        FilterListItem(
            label = R.string.country,
            value = "Россия",
            onClick = {},
            trailing = { TrailingClose({}) }
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewFilterListItem3() {
    DiplomaTheme {
        FilterListItem(
            label = R.string.dont_show_without_salary,
            onClick = {},
            trailing = { TrailingCheckbox(false) }
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewFilterListItem4() {
    DiplomaTheme {
        FilterListItem(
            value = "Авиационная, вертолетная и аэрокосмическая промышленность",
            onClick = {},
            trailing = { TrailingRadio(true) }
        )
    }
}
