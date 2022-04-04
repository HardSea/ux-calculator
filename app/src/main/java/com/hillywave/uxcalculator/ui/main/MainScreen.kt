package com.hillywave.uxcalculator.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
	CalculatorGrid(
		modifier = Modifier
			.fillMaxSize()
	) {
		when (it) {
			is ButtonType.Numbers -> {
			}
			is ButtonType.Operation -> {
			}
			is ButtonType.Operator -> {
			}
		}
	}
}
