package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hillywave.uxcalculator.R
import com.hillywave.uxcalculator.ui.theme.Grey870
import com.hillywave.uxcalculator.ui.theme.textSelectionColors

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HistoryContent(
	items: List<String>,
) {
	if (items.isEmpty()) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.padding(vertical = 8.dp)
		) {
			Text(modifier = Modifier.align(Alignment.Center), text = stringResource(R.string.empty_history_text))
		}
	}
	LazyColumn(
		modifier = Modifier.defaultMinSize(10.dp)
	) {
		items.forEachIndexed { index, item ->
			item {
				CompositionLocalProvider(LocalTextSelectionColors provides textSelectionColors()) {
					SelectionContainer {
						Column {
							val textScroll = rememberScrollState(0)
							Text(
								modifier = Modifier
									.padding(start = 8.dp, end = 8.dp, top = 8.dp)
									.horizontalScroll(textScroll),
								text = item,
								fontSize = 20.sp
							)
							if (items.size != 1 && index != items.lastIndex) {
								Divider(
									modifier = Modifier.padding(top = 8.dp, start = 8.dp, end = 8.dp),
									thickness = 1.dp,
									color = Grey870
								)
							}
						}
					}
				}
			}
		}
	}
}
