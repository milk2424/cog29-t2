package ru.practicum.android.diploma.presentation.filter.components

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.practicum.android.diploma.R

@Composable
fun TrailingCheckbox(checked: Boolean) {
    Icon(
        painter = painterResource(
            if (checked) R.drawable.check_box_on__24px
            else R.drawable.check_box_off__24px
        ),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.primary
    )
}
