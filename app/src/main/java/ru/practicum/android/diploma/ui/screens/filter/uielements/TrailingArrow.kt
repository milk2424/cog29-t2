package ru.practicum.android.diploma.ui.screens.filter.uielements

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import ru.practicum.android.diploma.R

@Composable
fun TrailingArrow() {
    Icon(
        painter = painterResource(id = R.drawable.arrow_forward_24px),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onBackground
    )
}
