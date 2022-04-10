package com.hillywave.uxcalculator.ui.main.components

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.hillywave.uxcalculator.ui.theme.Grey870

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun HistoryContent(
	items: List<String>,
) {
	Divider(modifier = Modifier.padding(top = 8.dp), thickness = 1.dp, color = Grey870)

	LazyColumn(
		modifier = Modifier.defaultMinSize(10.dp)
	) {
		items.forEach { item ->
			item {
				Text(
					text = item
				)
				Divider(modifier = Modifier.padding(top = 8.dp), thickness = 1.dp, color = Grey870)
			}
		}
	}
}
