package ru.practicum.android.diploma.presentation.root

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
import ru.practicum.android.diploma.presentation.favorites.FavoritesScreen
import ru.practicum.android.diploma.presentation.filter.FilterScreen
import ru.practicum.android.diploma.presentation.navigation.BottomNavBar
import ru.practicum.android.diploma.presentation.navigation.NavRoute
import ru.practicum.android.diploma.presentation.search.SearchScreen
import ru.practicum.android.diploma.presentation.team.TeamScreen
import ru.practicum.android.diploma.presentation.vacancy.VacancyScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentEntry?.destination?.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (currentRoute in
                listOf(NavRoute.Tab.Main.route, NavRoute.Tab.Favorites.route, NavRoute.Tab.Team.route)
            ) {
                BottomNavBar(navController, currentRoute)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = NavRoute.Tab.Main.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(NavRoute.Tab.Main.route) { SearchScreen(navController) }
            composable(NavRoute.Tab.Favorites.route) { FavoritesScreen(navController) }
            composable(NavRoute.Tab.Team.route) { TeamScreen() }
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
