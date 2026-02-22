package ru.practicum.android.diploma.ui.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ru.practicum.android.diploma.R

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?) {
    NavigationBar() {
        NavigationBarItem(
            selected = currentRoute == NavRoute.Search.route,
            onClick = { navController.navigate(NavRoute.Search.route) },
            icon = { },
            label = { Text(stringResource(R.string.tab_search)) }
        )
        NavigationBarItem(
            selected = currentRoute == NavRoute.Favorites.route,
            onClick = { navController.navigate(NavRoute.Favorites.route) },
            icon = { },
            label = { Text(stringResource(R.string.tab_favorites)) }
        )
        NavigationBarItem(
            selected = currentRoute == NavRoute.Team.route,
            onClick = { navController.navigate(NavRoute.Team.route) },
            icon = { },
            label = { Text(stringResource(R.string.tab_team)) }
        )
    }
}
