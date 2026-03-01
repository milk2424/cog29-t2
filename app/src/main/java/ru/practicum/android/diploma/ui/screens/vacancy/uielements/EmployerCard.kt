package ru.practicum.android.diploma.ui.screens.vacancy.uielements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.ui.theme.Dimens.iconCorners
import ru.practicum.android.diploma.ui.theme.Dimens.insetsZero
import ru.practicum.android.diploma.ui.theme.Dimens.logoMedium
import ru.practicum.android.diploma.ui.theme.Dimens.paddingLarge
import ru.practicum.android.diploma.ui.theme.Dimens.paddingXLarge
import ru.practicum.android.diploma.ui.theme.Dimens.spacer12

@Composable
fun EmployerCard(vacancy: Vacancy) {
    Card(
        shape = RoundedCornerShape(iconCorners),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.outline
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = insetsZero),
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = paddingXLarge),
    ) {
        Row(
            modifier = Modifier.padding(paddingLarge),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = vacancy.employer.logo,
                contentDescription = null,
                placeholder = painterResource(R.drawable.img_no_employer_logo),
                error = painterResource(R.drawable.img_no_employer_logo),
                modifier = Modifier.size(logoMedium)
            )
            Spacer(modifier = Modifier.width(spacer12))
            Column {
                Text(
                    text = vacancy.employer.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = vacancy.areaName,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}
