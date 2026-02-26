package ru.practicum.android.diploma.domain.core.use_case

import ru.practicum.android.diploma.domain.core.repository.ExternalNavigator

class OpenInBrowserUseCase(private val navigator: ExternalNavigator) {
    operator fun invoke(url: String) {
        navigator.openBrowser(url)
    }
}
