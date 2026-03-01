package ru.practicum.android.diploma.ui.screens.vacancy.uielements

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.models.VacancyContacts
import ru.practicum.android.diploma.ui.theme.Dimens.spacer16
import ru.practicum.android.diploma.ui.theme.Dimens.spacer24
import ru.practicum.android.diploma.ui.theme.Dimens.spacer4

@Composable
fun ContactsBlock(contacts: VacancyContacts) {
    Spacer(modifier = Modifier.height(spacer24))
    Text(
        text = stringResource(R.string.contacts),
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.onBackground,
    )
    Spacer(modifier = Modifier.height(spacer16))
    contacts.name?.takeIf { it.isNotBlank() }?.let {
        Text(
            text = stringResource(R.string.contact_person),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(spacer4))
        Text(
            text = it,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(spacer16))
    }
    contacts.email?.takeIf { it.isNotBlank() }?.let {
        Text(
            text = stringResource(R.string.e_mail),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(spacer4))
        Text(
            text = it,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(spacer16))
    }
    contacts.phone?.forEach { phone ->
        Text(
            text = stringResource(R.string.phone),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(spacer4))
        Text(
            text = phone,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(spacer16))
    }
}
