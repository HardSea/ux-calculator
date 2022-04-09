package com.hillywave.uxcalculator.ui.main

import androidx.lifecycle.ViewModel
import com.hillywave.uxcalculator.domain.MainController
import com.hillywave.uxcalculator.ui.main.entity.ButtonType
import com.hillywave.uxcalculator.ui.main.entity.InstrumentType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
	private val mainController: MainController
) : ViewModel() {

	val inputState = mainController.calculationFlow()
	val resultState = mainController.resultFlow()

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
