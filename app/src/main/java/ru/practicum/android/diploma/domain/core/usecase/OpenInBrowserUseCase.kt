package ru.practicum.android.diploma.domain.core.usecase

import ru.practicum.android.diploma.domain.core.repository.ExternalNavigator

class OpenInBrowserUseCase(private val navigator: ExternalNavigator) {
    operator fun invoke(url: String) {
        navigator.openBrowser(url)
    }
}
