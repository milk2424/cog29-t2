package ru.practicum.android.diploma.ui.navigation

import ru.practicum.android.diploma.R

sealed class NavRoute(val route: String, val labelRes: Int = 0, val iconRes: Int = 0) {
    object Main : NavRoute("main", R.string.tab_main, R.drawable.main_24px)
    object Favorites : NavRoute("favorites", R.string.tab_favorites, R.drawable.favorites_on__24px)
    object Team : NavRoute("team", R.string.tab_team, R.drawable.team_24px)
    object VacancyDetails : NavRoute("vacancy/{id}")
    object Filter : NavRoute("filter")
}
