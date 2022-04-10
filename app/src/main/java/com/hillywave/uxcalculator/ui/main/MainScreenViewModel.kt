package com.hillywave.uxcalculator.ui.main

import androidx.lifecycle.ViewModel
import com.hillywave.uxcalculator.data.FlowRepository
import com.hillywave.uxcalculator.domain.MainController
import com.hillywave.uxcalculator.domain.Result
import com.hillywave.uxcalculator.ui.main.entity.ButtonType
import com.hillywave.uxcalculator.ui.main.entity.InstrumentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
	private val mainController: MainController,
	flowRepository: FlowRepository
) : ViewModel() {

	val inputState: Flow<Result> = flowRepository.calculationFlow()
	val resultState: Flow<Result> = flowRepository.resultFlow()

	fun onButtonClick(type: ButtonType) {
		when (type) {
			is ButtonType.Numbers -> mainController.handleNumber(type.text)
			ButtonType.Operator.PLUS -> mainController.plus()
			ButtonType.Operator.MINUS -> mainController.minus()
			ButtonType.Operator.DIVIDE -> mainController.divide()
			ButtonType.Operator.MULTIPLY -> mainController.multiply()
			ButtonType.Operator.EMPTY -> {}
			ButtonType.Operation.CALCULATE -> mainController.calculate()
			ButtonType.Operation.CLEAR -> mainController.clear()
		}
	}

	fun onInstrumentClick(type: InstrumentType) {
		when (type) {
			InstrumentType.BACKSPACE -> mainController.delete()
			InstrumentType.HISTORY -> {
			}
		}
	}
}
