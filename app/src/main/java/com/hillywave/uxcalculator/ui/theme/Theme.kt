package com.hillywave.uxcalculator.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
	primary = Grey950,
	secondary = Grey850,
	onPrimary = Color.White,
	primaryVariant = Red450
)

@Composable
fun UXCalculatorTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
	val colors = DarkColorPalette

	MaterialTheme(
		colors = colors,
		typography = Typography,
		shapes = Shapes,
		content = content
	)
}