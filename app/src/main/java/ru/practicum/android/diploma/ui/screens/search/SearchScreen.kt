package ru.practicum.android.diploma.ui.screens.search

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(navController: NavController, viewModel: SearchViewModel = koinViewModel()) {
    viewModel.performSearch("Data Scientist")
    Text("Main Screen")
}
