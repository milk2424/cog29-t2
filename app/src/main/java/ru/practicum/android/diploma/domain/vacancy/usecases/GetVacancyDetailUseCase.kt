package ru.practicum.android.diploma.domain.vacancy.usecases

import ru.practicum.android.diploma.domain.api.VacancyRepository

class GetVacancyDetailUseCase(
    private val repository: VacancyRepository
) {
    operator fun invoke(id: String) =
        repository.getVacancyDetail(id)
}
