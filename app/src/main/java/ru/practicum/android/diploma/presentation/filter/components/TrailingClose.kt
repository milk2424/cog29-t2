package ru.practicum.android.diploma.presentation.filter.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R

@Composable
fun TrailingClose(onClick: () -> Unit) {
    IconButton(onClick = onClick, modifier = Modifier.size(24.dp)) {
        Icon(
            painter = painterResource(id = R.drawable.close_24px),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}
