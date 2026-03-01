package ru.practicum.android.diploma.ui.screens.search.uielements

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.Vacancy
import ru.practicum.android.diploma.ui.theme.Dimens.iconCorners
import ru.practicum.android.diploma.ui.theme.Dimens.logoBorderThickness
import ru.practicum.android.diploma.ui.theme.Dimens.logoMedium
import ru.practicum.android.diploma.ui.theme.Dimens.paddingMedium
import ru.practicum.android.diploma.ui.theme.Dimens.paddingSmall
import ru.practicum.android.diploma.util.SalaryStrings

@Composable
fun VacancyListItem(vacancy: Vacancy, salaryStrings: SalaryStrings, onVacancyClicked: ((String) -> Unit)) {
    val imageShape = RoundedCornerShape(iconCorners)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = paddingSmall)
            .clickable {
                onVacancyClicked(vacancy.id)
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .size(logoMedium)
                .clip(imageShape)
                .border(
                    width = logoBorderThickness,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    shape = imageShape
                ),
            model = vacancy.employer.logo,
            placeholder = painterResource(R.drawable.img_no_employer_logo),
            error = painterResource(R.drawable.img_no_employer_logo),
            contentScale = ContentScale.Inside,
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(paddingMedium))
        VacancyMainInfoColumn(vacancy, salaryStrings)
    }
}
