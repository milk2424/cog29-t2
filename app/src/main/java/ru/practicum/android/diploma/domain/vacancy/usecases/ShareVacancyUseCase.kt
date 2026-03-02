package ru.practicum.android.diploma.domain.vacancy.usecases

import ru.practicum.android.diploma.domain.core.repository.ExternalNavigator
import ru.practicum.android.diploma.domain.models.Vacancy

class ShareVacancyUseCase(
    private val externalNavigator: ExternalNavigator
) {
    operator fun invoke(vacancy: Vacancy) {
        externalNavigator.shareVacancy(vacancy)
    }
}
