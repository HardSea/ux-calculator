package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.hillywave.uxcalculator.ui.theme.Grey550
import com.hillywave.uxcalculator.ui.theme.Inter

@Composable
fun ResultPanel(
	modifier: Modifier = Modifier,
	value: String,
) {
	Text(
		modifier = modifier.fillMaxWidth(),
		text = value,
		fontFamily = Inter,
		fontSize = 48.sp,
		color = Grey550,
		textAlign = TextAlign.End
	)
}
