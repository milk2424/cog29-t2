package ru.practicum.android.diploma.ui.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import ru.practicum.android.diploma.ui.theme.Dimens

@Composable
fun BottomNavBar(navController: NavController, currentRoute: String?) {
    val items = remember { listOf(NavRoute.Tab.Main, NavRoute.Tab.Favorites, NavRoute.Tab.Team) }

    Column {
        HorizontalDivider(thickness = Dimens.bottomNavBarDividerThickness, color = MaterialTheme.colorScheme.outline)
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background
        ) {
            items.forEach { route ->
                NavigationBarItem(
                    selected = currentRoute == route.route, onClick = {
                    navController.navigate(route.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }, icon = {
                    Icon(
                        painter = painterResource(route.iconRes),
                        contentDescription = stringResource(route.labelRes)
                    )
                }, colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    indicatorColor = Color.Transparent
                ), label = {
                    Text(
                        text = stringResource(route.labelRes), style = MaterialTheme.typography.bodyMedium
                    )
                })
            }
        }
    }
}
