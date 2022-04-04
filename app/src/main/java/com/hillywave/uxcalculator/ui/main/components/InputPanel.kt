package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.hillywave.uxcalculator.ui.theme.Inter

@Composable
fun InputPanel(
	modifier: Modifier = Modifier,
	value: String
) {
	Text(
		modifier = modifier.fillMaxWidth(),
		text = value,
		fontFamily = Inter,
		fontSize = 56.sp,
		color = MaterialTheme.colors.onPrimary,
		textAlign = TextAlign.End
	)
}
