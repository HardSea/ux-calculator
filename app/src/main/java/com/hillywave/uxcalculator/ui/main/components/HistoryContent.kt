package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hillywave.uxcalculator.ui.theme.Grey870

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
			Text(modifier = Modifier.align(Alignment.Center), text = "It's empty here")
		}
	}
	LazyColumn(
		modifier = Modifier.defaultMinSize(10.dp)
	) {
		items.forEachIndexed { index, item ->
			item {
				val textScroll = rememberScrollState(0)
				Text(
					modifier = Modifier
						.padding(start = 8.dp, end = 8.dp, top = 8.dp)
						.horizontalScroll(textScroll),
					text = item,
					fontSize = 20.sp
				)
				if (items.size != 1 && index != items.lastIndex) {
					Divider(modifier = Modifier.padding(top = 8.dp), thickness = 1.dp, color = Grey870)
				}
			}
		}
	}
}
