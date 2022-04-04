package com.hillywave.uxcalculator.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hillywave.uxcalculator.ui.theme.Grey870

@Composable
fun MainScreen() {
	Column(modifier = Modifier.fillMaxSize()) {

		Divider(modifier = Modifier.padding(bottom = 24.dp, start = 18.dp, end = 18.dp, top = 18.dp), thickness = 1.dp, color = Grey870)
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
}
