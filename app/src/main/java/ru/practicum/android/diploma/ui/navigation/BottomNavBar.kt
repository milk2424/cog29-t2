package ru.practicum.android.diploma.ui.navigation

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?) {
    val items = listOf(NavRoute.Search, NavRoute.Favorites, NavRoute.Team)
    NavigationBar {
        items.forEach { route ->
            NavigationBarItem(
                selected = currentRoute == route.route,
                onClick = {
                    navController.navigate(route.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { },
                label = { Text(stringResource(route.labelRes)) }
            )
        }
    }
}
