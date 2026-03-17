package ru.practicum.android.diploma.presentation.filter.components

import android.content.res.Configuration
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import ru.practicum.android.diploma.core.ui.theme.DiplomaTheme

@Composable
fun SalaryTextField(
    salary: String,
    onSalaryChange: (String) -> Unit,
    onClear: () -> Unit,
) {
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val labelColor = when {
        isFocused -> MaterialTheme.colorScheme.primary
        salary.isNotEmpty() -> MaterialTheme.colorScheme.onSurface
        else -> MaterialTheme.colorScheme.secondary
    }
    BasicTextField(
        value = salary,
        onValueChange = { if (it.all { c -> c.isDigit() }) onSalaryChange(it) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge.copy(
            color = MaterialTheme.colorScheme.onSurface
        ),
        cursorBrush = SolidColor(value = labelColor),
        decorationBox = { innerTextField ->
            SalaryDecorationBox(
                salary = salary,
                labelColor = labelColor,
                onClear = onClear,
                focusRequester = focusRequester,
                innerTextField = innerTextField
            )

        },
        modifier = Modifier
            .focusRequester(focusRequester)
            .onFocusChanged { isFocused = it.isFocused }
    )
}

@Preview(name = "Light", uiMode = Configuration.UI_MODE_NIGHT_NO, showBackground = true)
@Preview(name = "Dark", uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
private fun PreviewSalaryTextField() {
    DiplomaTheme {
        SalaryTextField(
            salary = "50000",
            onSalaryChange = { },
            onClear = { }
        )
    }
}
