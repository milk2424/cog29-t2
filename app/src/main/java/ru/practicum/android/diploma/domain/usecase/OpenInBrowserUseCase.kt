package ru.practicum.android.diploma.domain.usecase

import ru.practicum.android.diploma.core.navigation.ExternalNavigator

class OpenInBrowserUseCase(private val navigator: ExternalNavigator) {
    operator fun invoke(url: String) {
        navigator.openBrowser(url)
    }
}
