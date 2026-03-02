package ru.practicum.android.diploma.presentation.navigation

import ru.practicum.android.diploma.R

sealed class NavRoute(val route: String) {

    sealed class Tab(
        route: String,
        val labelRes: Int,
        val iconRes: Int
    ) : NavRoute(route) {
        object Main : Tab("main", R.string.tab_main, R.drawable.main_24px)
        object Favorites : Tab("favorites", R.string.tab_favorites, R.drawable.favorites_on__24px)
        object Team : Tab("team", R.string.tab_team, R.drawable.team_24px)
    }

    object VacancyDetails : NavRoute("vacancy/{id}")
    object Filter : NavRoute("filter")
}
