package com.hillywave.uxcalculator.ui.main

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hillywave.uxcalculator.ui.main.components.BasicButton

@Composable
fun CalculatorGrid(
	modifier: Modifier = Modifier,
	onButtonClick: (ButtonType) -> Unit
) {
	LazyColumn(
		modifier = modifier
			.padding(horizontal = 8.dp)
			.fillMaxSize()
	) {
		calculatorButtons().chunked(4).forEachIndexed { i, subList ->
			item {

				Row(modifier = Modifier.padding(top = if (i != 0) 18.dp else 0.dp)) {
					subList.forEachIndexed { j, buttonType ->
						BasicButton(
							modifier = Modifier.padding(horizontal = 8.dp),
							type = buttonType,
							onClick = onButtonClick
						)
					}
				}
			}
		}
	}
}
