package com.hillywave.uxcalculator.ui.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Grey950 = Color(0xFF151515)
val Grey870 = Color(0xFF424242)
val Grey850 = Color(0xFF343434)
val Grey550 = Color(0xFF969696)

val Green450 = Color(0xFF4CAF50)
val Red450 = Color(0xFFFF5959)

@Composable
fun textSelectionColors() = TextSelectionColors(
	handleColor = MaterialTheme.colors.primaryVariant,
	backgroundColor = MaterialTheme.colors.primaryVariant.copy(alpha = 0.4f)
)
