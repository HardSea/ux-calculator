package com.hillywave.uxcalculator.ui.main

import androidx.lifecycle.ViewModel
import com.hillywave.uxcalculator.domain.MainController
import com.hillywave.uxcalculator.domain.Result
import com.hillywave.uxcalculator.ui.main.entity.ButtonType
import com.hillywave.uxcalculator.ui.main.entity.InstrumentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class MainScreenViewModel @Inject constructor(
	private val mainController: MainController
) : ViewModel() {

	private val _inputState = MutableStateFlow("")
	val inputState = _inputState.asStateFlow()

	private val _resultState = MutableStateFlow("")
	val resultState = _resultState.asStateFlow()

	fun onButtonClick(type: ButtonType) {
		when (type) {
			is ButtonType.Numbers -> handleResult(mainController.handleNumber(type.text))
			ButtonType.Operator.PLUS -> handleResult(mainController.plus())
			ButtonType.Operator.MINUS -> handleResult(mainController.minus())
			ButtonType.Operator.DIVIDE -> handleResult(mainController.divide())
			ButtonType.Operator.MULTIPLY -> handleResult(mainController.multiply())
			ButtonType.Operator.EMPTY -> {}
			ButtonType.Operation.CALCULATE -> handleResult(mainController.calculate())
			ButtonType.Operation.CLEAR -> clear()
		}
	}

	fun onInstrumentClick(type: InstrumentType) {
		when (type) {
			InstrumentType.BACKSPACE -> {
				_inputState.value = _inputState.value.dropLast(1)
			}
			InstrumentType.HISTORY -> {
			}
		}
	}

	private fun clear() {
		// todo mainController.clear()
	}

	private fun handleResult(result: Result) {
		when (result) {
			is Result.Success -> {
				_inputState.value = result.value
			}
			is Result.Error -> {
			}
			Result.Nothing -> {
			}
		}
	}
}
