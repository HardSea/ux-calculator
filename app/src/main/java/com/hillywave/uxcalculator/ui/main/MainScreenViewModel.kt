package com.hillywave.uxcalculator.ui.main

import androidx.lifecycle.ViewModel
import com.hillywave.uxcalculator.domain.MainController
import com.hillywave.uxcalculator.ui.main.entity.ButtonType
import com.hillywave.uxcalculator.ui.main.entity.InstrumentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

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
			is ButtonType.Numbers -> {
				_inputState.value += type.text
			}
			is ButtonType.Operator -> {
				_inputState.value += type.text
			}
			is ButtonType.Operation -> {
			}
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
}
