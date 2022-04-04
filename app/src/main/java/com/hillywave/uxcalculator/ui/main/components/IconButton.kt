package com.hillywave.uxcalculator.ui.main.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
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
	Box(
		modifier = modifier.clickable(
			interactionSource = remember { MutableInteractionSource() },
			indication = rememberRipple(bounded = false, radius = 24.dp),
			onClick = {
				haptic.performHapticFeedback(HapticFeedbackType.LongPress)
				onClick()
			}
		),
		contentAlignment = Alignment.Center
	) {
		Icon(
			painter = painterResource(id = iconRes),
			contentDescription = null,
			tint = MaterialTheme.colors.onPrimary
		)
	}
}
