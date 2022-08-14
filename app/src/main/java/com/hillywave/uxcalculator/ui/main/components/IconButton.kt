package com.hillywave.uxcalculator.ui.main.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun IconButton(
    modifier: Modifier = Modifier,
    @DrawableRes iconRes: Int,
    onClick: () -> Unit
) {
    val haptic = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
			.indication(interactionSource, rememberRipple(radius = 24.dp, bounded = false))
			.pointerInput(Unit) {
				detectTapGestures(
					onPress = { offset: Offset ->
						val press = PressInteraction.Press(offset)
						interactionSource.emit(press)

						haptic.performHapticFeedback(HapticFeedbackType.LongPress)
						onClick()

						tryAwaitRelease()
						interactionSource.emit(PressInteraction.Release(press))
					}
				)
			},
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = null,
            tint = MaterialTheme.colors.onPrimary
        )
    }
}
