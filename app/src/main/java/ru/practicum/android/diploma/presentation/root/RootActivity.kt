package ru.practicum.android.diploma.presentation.root

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.core.ui.theme.DiplomaTheme

class RootActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiplomaTheme {
                MainScreen()
            }
        }
        networkRequestExample(accessToken = BuildConfig.API_ACCESS_TOKEN)
    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }

}
