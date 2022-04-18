package com.hillywave.uxcalculator.ui

import android.os.Bundle
import android.util.Log
import android.util.TimeUtils
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
import com.hillywave.uxcalculator.ui.main.MainScreenViewModel
import com.hillywave.uxcalculator.ui.settings.SettingsScreen
import com.hillywave.uxcalculator.ui.theme.UXCalculatorTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

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
			MainScreen(hiltViewModel<MainScreenViewModel>())
		}
		composable(SETTINGS_SCREEN_ROUTE) {
			SettingsScreen()
		}
	}
}

private const val MAIN_SCREEN_ROUTE = "mainScreen"
private const val SETTINGS_SCREEN_ROUTE = "settingsScreen"
