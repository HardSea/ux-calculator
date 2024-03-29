package com.hillywave.uxcalculator.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hillywave.uxcalculator.ui.main.MainScreen
import com.hillywave.uxcalculator.ui.settings.SettingsScreen
import com.hillywave.uxcalculator.ui.theme.UXCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            UXCalculatorTheme {
                window.statusBarColor = MaterialTheme.colors.primary.toArgb()
                window.navigationBarColor = MaterialTheme.colors.primary.toArgb()

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = MaterialTheme.colors.background
                ) {
                    Navigation()
                }
            }
        }
    }
}

@Composable
private fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MAIN_SCREEN_ROUTE
    ) {
        composable(MAIN_SCREEN_ROUTE) {
            MainScreen(hiltViewModel(), navController)
        }
        composable(SETTINGS_SCREEN_ROUTE) {
            SettingsScreen(hiltViewModel(), navController)
        }
    }
}

const val MAIN_SCREEN_ROUTE = "mainScreen"
const val SETTINGS_SCREEN_ROUTE = "settingsScreen"
