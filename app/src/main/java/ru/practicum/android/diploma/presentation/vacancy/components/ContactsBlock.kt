package ru.practicum.android.diploma.presentation.vacancy.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.domain.model.VacancyContacts

@Composable
fun ContactsBlock(contacts: VacancyContacts) {
    Spacer(modifier = Modifier.height(24.dp))
    TitleBlock(R.string.contacts)
    Spacer(modifier = Modifier.height(16.dp))
    contacts.name?.takeIf { it.isNotBlank() }?.let {
        TextBlock(title = R.string.contact_person, text = it)
        Spacer(modifier = Modifier.height(16.dp))
    }
    contacts.email?.takeIf { it.isNotBlank() }?.let {
        TextBlock(title = R.string.e_mail, text = it)
        Spacer(modifier = Modifier.height(16.dp))
    }
    contacts.phone?.forEach { phone ->
        TextBlock(title = R.string.phone, text = phone)
        Spacer(modifier = Modifier.height(16.dp))
    }
}
