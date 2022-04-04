package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hillywave.uxcalculator.ui.main.ButtonType

@Composable
fun RowScope.BasicButton(
	modifier: Modifier = Modifier,
	type: ButtonType,
	onClick: (type: ButtonType) -> Unit
) {
	Button(
		modifier = modifier
			.weight(1f)
			.aspectRatio(1f),
		onClick = { onClick(type) },
		shape = RoundedCornerShape(8.dp),
		colors = ButtonDefaults.buttonColors(
			backgroundColor = MaterialTheme.colors.secondary
		)
	) {
		Text(text = type.text, color = MaterialTheme.colors.onPrimary, fontSize = 36.sp)
	}
}

@Preview()
@Composable
private fun BasicButtonPreview() {
	Row {
		BasicButton(type = ButtonType.Numbers.ONE) {}
		BasicButton(type = ButtonType.Numbers.TWO) {}
		BasicButton(type = ButtonType.Numbers.THREE) {}
		BasicButton(type = ButtonType.Numbers.FOUR) {}
	}
}
