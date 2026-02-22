package ru.practicum.android.diploma.ui.navigation

sealed class NavRoute(val route: String) {
    object Search: NavRoute("search")
    object Favorites: NavRoute("favorites")
    object Team: NavRoute("team")
    object VacancyDetails: NavRoute("vacancy/{id}")
    object Filter: NavRoute("filter")
}
