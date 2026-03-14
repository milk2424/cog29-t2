package ru.practicum.android.diploma.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.practicum.android.diploma.R

@Composable
fun BottomNavBar(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val items = remember {
        listOf(
            TabItem(Main, R.string.tab_main, R.drawable.main_24px),
            TabItem(Favorites, R.string.tab_favorites, R.drawable.favorites_on__24px),
            TabItem(Team, R.string.tab_team, R.drawable.team_24px)
        )
    }

    Column {
        HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline)
        NavigationBar(
            containerColor = MaterialTheme.colorScheme.background
        ) {
            items.fastForEach { item ->
                NavigationBarItem(
                    selected = currentBackStackEntry?.destination?.hasRoute(item.route::class) == true,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = item.iconRes),
                            contentDescription = stringResource(id = item.labelRes)
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                        indicatorColor = Color.Transparent
                    ),
                    label = {
                        Text(
                            text = stringResource(id = item.labelRes),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                )
            }
        }
    }
}

data class TabItem(
    val route: Any,
    val labelRes: Int,
    val iconRes: Int
)
