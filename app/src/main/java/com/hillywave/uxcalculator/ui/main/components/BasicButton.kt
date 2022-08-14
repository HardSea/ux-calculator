package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hillywave.uxcalculator.ui.main.entity.ButtonType
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
    val interactionSource = remember { MutableInteractionSource() }
    val shape = RoundedCornerShape(8.dp)

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
    Box(
        modifier = modifier
			.weight(1f)
			.aspectRatio(1f)
			.clip(shape)
			.indication(interactionSource, rememberRipple(bounded = true))
			.background(color = buttonColor, shape = shape)
			.pointerInput(Unit) {
				detectTapGestures(
					onPress = { offset: Offset ->
						val press = PressInteraction.Press(offset)
						interactionSource.emit(press)

						onClick(type)
						haptic.performHapticFeedback(HapticFeedbackType.LongPress)

						tryAwaitRelease()
						interactionSource.emit(PressInteraction.Release(press))
					}
				)
			},
        contentAlignment = Alignment.Center
    ) {
        Text(text = type.text, color = symbolColor, fontSize = 36.sp, fontFamily = Inter)
    }
}
