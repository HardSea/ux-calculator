package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.hillywave.uxcalculator.R
import com.hillywave.uxcalculator.ui.main.InstrumentType

@Composable
fun InstrumentPanel(
	modifier: Modifier = Modifier,
	onInstrumentClick: (InstrumentType) -> Unit
) {
	Row(
		modifier = modifier
			.fillMaxWidth()
			.padding(horizontal = 18.dp)
	) {
		Icon(
			modifier = Modifier.clickable {
				onInstrumentClick(InstrumentType.HISTORY)
			},
			painter = painterResource(id = R.drawable.ic_history),
			contentDescription = null,
			tint = MaterialTheme.colors.onPrimary
		)
		Divider(modifier = Modifier.weight(1f), thickness = 0.dp, color = Color.Transparent)
		Icon(
			modifier = Modifier.clickable {
				onInstrumentClick(InstrumentType.HISTORY)
			},
			painter = painterResource(id = R.drawable.ic_backspace),
			contentDescription = null,
			tint = MaterialTheme.colors.onPrimary
		)
	}
}
