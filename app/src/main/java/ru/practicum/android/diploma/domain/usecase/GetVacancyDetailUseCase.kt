package ru.practicum.android.diploma.domain.usecase

import ru.practicum.android.diploma.domain.repository.VacancyRepository

class GetVacancyDetailUseCase(
    private val repository: VacancyRepository
) {
    operator fun invoke(id: String) =
        repository.getVacancyDetail(id)
}
