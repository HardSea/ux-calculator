package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.hillywave.uxcalculator.ui.theme.Inter
import com.hillywave.uxcalculator.ui.theme.textSelectionColors

@Composable
fun InputPanel(
	modifier: Modifier = Modifier,
	value: String
) {
	val scroll = rememberScrollState(Int.MAX_VALUE)
	CompositionLocalProvider(LocalTextSelectionColors provides textSelectionColors()) {
		SelectionContainer(modifier = modifier.fillMaxWidth()) {
			Text(
				modifier = Modifier.horizontalScroll(state = scroll, reverseScrolling = true),
				text = value,
				fontFamily = Inter,
				fontSize = 56.sp,
				color = MaterialTheme.colors.onPrimary,
				textAlign = TextAlign.End,
				maxLines = 1
			)
		}
	}
}
