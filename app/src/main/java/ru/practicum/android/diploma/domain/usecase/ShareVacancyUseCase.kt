package ru.practicum.android.diploma.domain.usecase

import ru.practicum.android.diploma.core.navigation.ExternalNavigator
import ru.practicum.android.diploma.domain.model.Vacancy

class ShareVacancyUseCase(
    private val externalNavigator: ExternalNavigator
) {
    operator fun invoke(vacancy: Vacancy) {
        externalNavigator.shareVacancy(vacancy)
    }
}
