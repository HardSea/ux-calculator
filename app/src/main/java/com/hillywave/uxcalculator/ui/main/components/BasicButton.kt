package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hillywave.uxcalculator.ui.main.ButtonType
import com.hillywave.uxcalculator.ui.theme.Green450
import com.hillywave.uxcalculator.ui.theme.Inter
import com.hillywave.uxcalculator.ui.theme.Red450

@Composable
fun RowScope.BasicButton(
	modifier: Modifier = Modifier,
	type: ButtonType,
	onClick: (type: ButtonType) -> Unit
) {
	val haptic = LocalHapticFeedback.current
	val symbolColor = when (type) {
		is ButtonType.Operator -> Green450
		is ButtonType.Operation -> MaterialTheme.colors.secondary
		else -> MaterialTheme.colors.onPrimary
	}
	val buttonColor = when (type) {
		is ButtonType.Operation -> when (type) {
			ButtonType.Operation.CALCULATE -> Green450
			ButtonType.Operation.CLEAR -> Red450
		}
		else -> MaterialTheme.colors.secondary
	}
	Button(
		modifier = modifier
			.weight(1f)
			.aspectRatio(1f),
		onClick = {
			haptic.performHapticFeedback(HapticFeedbackType.LongPress)
			onClick(type)
		},
		shape = RoundedCornerShape(8.dp),
		colors = ButtonDefaults.buttonColors(backgroundColor = buttonColor)
	) {
		Text(text = type.text, color = symbolColor, fontSize = 36.sp, fontFamily = Inter)
	}
}
