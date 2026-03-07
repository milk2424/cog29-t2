package ru.practicum.android.diploma.presentation.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.koin.androidx.compose.koinViewModel
import ru.practicum.android.diploma.presentation.favorites.FavoritesScreen
import ru.practicum.android.diploma.presentation.filter.FilterScreen
import ru.practicum.android.diploma.presentation.filter.industry.IndustrySelectionScreen
import ru.practicum.android.diploma.presentation.navigation.BottomNavBar
import ru.practicum.android.diploma.presentation.navigation.Favorites
import ru.practicum.android.diploma.presentation.navigation.Filter
import ru.practicum.android.diploma.presentation.navigation.Main
import ru.practicum.android.diploma.presentation.navigation.Team
import ru.practicum.android.diploma.presentation.navigation.VacancyDetails
import ru.practicum.android.diploma.presentation.search.SearchScreen
import ru.practicum.android.diploma.presentation.team.TeamScreen
import ru.practicum.android.diploma.presentation.vacancy.VacancyScreen
import ru.practicum.android.diploma.presentation.navigation.IndustrySelection

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentEntry by navController.currentBackStackEntryAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            val showBottomBar = currentEntry?.destination?.run {
                hasRoute<Main>() || hasRoute<Favorites>() || hasRoute<Team>()
            } == true
            if (showBottomBar) {
                BottomNavBar(navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Main,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable<Main> { SearchScreen(navController) }
            composable<Favorites> { FavoritesScreen(navController) }
            composable<Team> { TeamScreen() }
            composable<Filter> { FilterScreen(navController) }
            composable<VacancyDetails> { backStackEntry ->
                val route = backStackEntry.toRoute<VacancyDetails>()
                VacancyScreen(navController, route.id)
            }
            composable<IndustrySelection> {
                IndustrySelectionScreen(navController = navController)
            }
        }
    }
}
