package ru.practicum.android.diploma.ui.navigation

import ru.practicum.android.diploma.R

sealed class NavRoute(val route: String, val labelRes: Int = 0) {
    object Search : NavRoute("search", R.string.tab_search)
    object Favorites : NavRoute("favorites", R.string.tab_favorites)
    object Team : NavRoute("team", R.string.tab_team)
    object VacancyDetails : NavRoute("vacancy/{id}")
    object Filter : NavRoute("filter")
}
