package com.hillywave.uxcalculator.ui.main

import androidx.lifecycle.ViewModel
import com.hillywave.uxcalculator.data.core.FlowRepository
import com.hillywave.uxcalculator.domain.core.FlowController
import com.hillywave.uxcalculator.domain.core.MainController
import com.hillywave.uxcalculator.domain.core.Result
import com.hillywave.uxcalculator.ui.main.entity.ButtonType
import com.hillywave.uxcalculator.ui.main.entity.InstrumentType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val mainController: MainController,
    private val flowRepository: FlowRepository
) : ViewModel() {

    val calculationFlow: Flow<Result> = flowRepository.calculationFlow()
    val resultFlow: Flow<Result> = flowRepository.resultFlow()
    val historyFlow: Flow<List<String>> = flowRepository.historyFlow()

    fun onButtonClick(type: ButtonType) {
        flowRepository
        when (type) {
            is ButtonType.Numbers -> mainController.handleNumber(type.text)
            ButtonType.Operator.PLUS -> mainController.plus()
            ButtonType.Operator.MINUS -> mainController.minus()
            ButtonType.Operator.DIVIDE -> mainController.divide()
            ButtonType.Operator.MULTIPLY -> mainController.multiply()
            ButtonType.Operator.EMPTY -> {}
            ButtonType.Operation.CALCULATE -> mainController.calculate()
            ButtonType.Operation.CLEAR -> mainController.clear()
            ButtonType.Other.DOT -> mainController.handleDot()
        }
    }

    fun onInstrumentClick(type: InstrumentType) {
        when (type) {
            InstrumentType.BACKSPACE -> mainController.delete()
            else -> {}
        }
    }
}
