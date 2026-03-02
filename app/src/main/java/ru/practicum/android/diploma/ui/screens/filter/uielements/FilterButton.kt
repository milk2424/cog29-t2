package ru.practicum.android.diploma.ui.screens.filter.uielements

import android.content.res.Configuration
import android.widget.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.ui.theme.DiplomaTheme

@Composable
fun FilterButton(
    text: Int,           // string resource
    isPrimary: Boolean,  // true = синяя, false = прозрачная/красная
    onClick: () -> Unit,
) {
    Button()
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewFilterButton1() {
    DiplomaTheme {
        FilterButton(
            text = R.string.apply,
            isPrimary = true,
            onClick = {},
        )
    }
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PreviewFilterButton2() {
    DiplomaTheme {
        FilterButton(
            text = R.string.reset,
            isPrimary = false,
            onClick = {},
        )
    }
}
