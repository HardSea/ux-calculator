package com.hillywave.uxcalculator.ui.settings

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.hillywave.uxcalculator.ui.MAIN_SCREEN_ROUTE

@Composable
fun SettingsScreen(viewModel: SettingsViewModel, navController: NavHostController) {
    Button(onClick = { navController.popBackStack(MAIN_SCREEN_ROUTE, inclusive = false) }) {
        Text(text = "Save")
    }
}
