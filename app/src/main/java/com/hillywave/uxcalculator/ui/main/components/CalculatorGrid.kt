package com.hillywave.uxcalculator.ui.main.components

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.hillywave.uxcalculator.ui.main.calculatorButtons
import com.hillywave.uxcalculator.ui.main.entity.ButtonType

@Composable
fun CalculatorGrid(
	modifier: Modifier = Modifier,
	onButtonClick: (ButtonType) -> Unit
) {
	val context = LocalContext.current
	LazyColumn(modifier = modifier) {
		calculatorButtons().chunked(4).forEachIndexed { i, subList ->
			item {
				Row(modifier = Modifier.padding(top = if (i != 0) 18.dp else 0.dp)) {
					subList.forEach { buttonType ->
						BasicButton(
							modifier = Modifier.padding(horizontal = 8.dp),
							type = buttonType,
							onClick = {
								MediaPlayer.create(context, it.soundClickRes)?.start()
								onButtonClick(it)
							}
						)
					}
				}
			}
		}
	}
}
