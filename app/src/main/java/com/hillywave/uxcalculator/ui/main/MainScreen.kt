package com.hillywave.uxcalculator.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hillywave.uxcalculator.domain.Result
import com.hillywave.uxcalculator.ui.main.components.*
import com.hillywave.uxcalculator.ui.main.entity.InstrumentType
import com.hillywave.uxcalculator.ui.theme.Grey870
import com.hillywave.uxcalculator.ui.theme.Grey950
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreen(viewModel: MainScreenViewModel) {
	val coroutineScope = rememberCoroutineScope()

	val calculationState by viewModel.calculationFlow.collectAsState(Result.Nothing)
	val resultState by viewModel.resultFlow.collectAsState(Result.Nothing)
	val historyState by viewModel.historyFlow.collectAsState(emptyList())

	val historyBottomState = ModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)

	ModalBottomSheetLayout(
		modifier = Modifier.fillMaxSize(),
		sheetState = historyBottomState,
		sheetElevation = 0.dp,
		sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
		sheetContent = {
			HistoryContent(items = historyState)
		},
		sheetBackgroundColor = Grey950
	) {
		Column(
			modifier = Modifier
				.fillMaxHeight()
				.background(MaterialTheme.colors.primary)
		) {
			InputPanel(
				modifier = Modifier.padding(start = 18.dp, end = 18.dp, top = 24.dp),
				value = when (calculationState) {
					is Result.Success -> (calculationState as Result.Success).value
					else -> String()
				}
			)
			Divider(modifier = Modifier.weight(1f), thickness = 0.dp, color = Color.Transparent)
			ResultPanel(
				modifier = Modifier.padding(horizontal = 18.dp),
				value = when (resultState) {
					is Result.Success -> (resultState as Result.Success).value
					is Result.Error -> stringResource(id = (resultState as Result.Error).messageRes)
					else -> String()
				},
				isError = resultState is Result.Error
			)
			InstrumentPanel(
				modifier = Modifier
					.padding(top = 36.dp)
					.padding(horizontal = 18.dp),
				onInstrumentClick = {
					if (it == InstrumentType.HISTORY) {
						coroutineScope.launch {
							historyBottomState.show()
						}
					} else {
						viewModel.onInstrumentClick(it)
					}
				}
			)
			Divider(modifier = Modifier.padding(bottom = 24.dp, start = 18.dp, end = 18.dp, top = 18.dp), thickness = 1.dp, color = Grey870)
			CalculatorGrid(
				modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp),
				onButtonClick = { viewModel.onButtonClick(it) }
			)
		}
	}
}
