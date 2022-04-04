package com.hillywave.uxcalculator.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.hillywave.uxcalculator.ui.main.components.InputPanel
import com.hillywave.uxcalculator.ui.main.components.InstrumentPanel
import com.hillywave.uxcalculator.ui.main.components.ResultPanel
import com.hillywave.uxcalculator.ui.theme.Grey870

@Composable
fun MainScreen() {
	Column(
		modifier = Modifier
			.fillMaxHeight()
			.background(MaterialTheme.colors.primary)
	) {
		InputPanel(
			modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 24.dp),
			value = "4+4+4+4+4+4+4+4+4+4+4+4+4"
		)
		Divider(modifier = Modifier.weight(1f), thickness = 0.dp, color = Color.Transparent)
		ResultPanel(modifier = Modifier.padding(horizontal = 18.dp), value = "8")
		InstrumentPanel(
			modifier = Modifier
				.padding(top = 36.dp)
				.padding(horizontal = 18.dp)
		) {
			when (it) {
				InstrumentType.HISTORY -> {
				}
				InstrumentType.BACKSPACE -> {
				}
			}
		}
		Divider(modifier = Modifier.padding(bottom = 24.dp, start = 18.dp, end = 18.dp, top = 18.dp), thickness = 1.dp, color = Grey870)
		CalculatorGrid(modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp)) {
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
