package ru.practicum.android.diploma.presentation.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.utils.SalaryStrings
import ru.practicum.android.diploma.domain.model.Vacancy
import ru.practicum.android.diploma.presentation.common.components.AppImageLoader

@Composable
fun VacancyListItem(vacancy: Vacancy, salaryStrings: SalaryStrings, onVacancyClicked: ((String) -> Unit)) {
    val context = LocalContext.current
    val imageLoader = remember { AppImageLoader.get(context) }

    val imageShape = MaterialTheme.shapes.medium
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 8.dp)
            .clickable {
                onVacancyClicked(vacancy.id)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(48.dp)
                .clip(imageShape)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    shape = imageShape
                ),
            model = vacancy.employer.logo,
            imageLoader = imageLoader,
            placeholder = painterResource(R.drawable.img_no_employer_logo),
            error = painterResource(R.drawable.img_no_employer_logo),
            contentScale = ContentScale.Inside,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(12.dp))
        VacancyMainInfoColumn(vacancy, salaryStrings)
    }
}
