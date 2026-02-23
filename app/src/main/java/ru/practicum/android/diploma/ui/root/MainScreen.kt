package ru.practicum.android.diploma.ui.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.practicum.android.diploma.ui.favorites.FavoritesScreen
import ru.practicum.android.diploma.ui.filter.FilterScreen
import ru.practicum.android.diploma.ui.navigation.BottomNavBar
import ru.practicum.android.diploma.ui.navigation.NavRoute
import ru.practicum.android.diploma.ui.search.SearchScreen
import ru.practicum.android.diploma.ui.team.TeamScreen
import ru.practicum.android.diploma.ui.vacancy.VacancyScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute in listOf(
                    NavRoute.Main.route,
                    NavRoute.Favorites.route,
                    NavRoute.Team.route
                )
            ) {
                BottomNavBar(navController, currentRoute)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavRoute.Main.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavRoute.Main.route) { SearchScreen(navController) }
            composable(NavRoute.Favorites.route) { FavoritesScreen(navController) }
            composable(NavRoute.Team.route) { TeamScreen() }
            composable(NavRoute.VacancyDetails.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id") ?: ""
                if (id.isNotEmpty()) {
                    VacancyScreen(navController, id)
                } else {
                    navController.popBackStack()
                }
            }
            composable(NavRoute.Filter.route) { FilterScreen(navController) }
        }
    }
}
