package ru.practicum.android.diploma.presentation.vacancy.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.ui.theme.Dimens.spacer16
import ru.practicum.android.diploma.core.ui.theme.Dimens.spacer24
import ru.practicum.android.diploma.domain.model.VacancyContacts

@Composable
fun ContactsBlock(contacts: VacancyContacts) {
    Spacer(modifier = Modifier.height(spacer24))
    TitleBlock(R.string.contacts)
    Spacer(modifier = Modifier.height(spacer16))
    contacts.name?.takeIf { it.isNotBlank() }?.let {
        TextBlock(title = R.string.contact_person, text = it)
        Spacer(modifier = Modifier.height(spacer16))
    }
    contacts.email?.takeIf { it.isNotBlank() }?.let {
        TextBlock(title = R.string.e_mail, text = it)
        Spacer(modifier = Modifier.height(spacer16))
    }
    contacts.phone?.forEach { phone ->
        TextBlock(title = R.string.phone, text = phone)
        Spacer(modifier = Modifier.height(spacer16))
    }
}
