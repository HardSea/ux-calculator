package com.hillywave.uxcalculator.data

import com.hillywave.uxcalculator.data.core.MainRepository
import com.hillywave.uxcalculator.data.core.Part
import com.hillywave.uxcalculator.domain.core.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import javax.inject.Inject

class BaseMainRepository @Inject constructor(private val left: Part, private val right: Part) : MainRepository {

    private var state: CalculationState = CalculationState.LEFT_PART_CLEAR
    private var operation: Operation = Operation.Nothing

    private var calculationFlow: MutableStateFlow<Result> = MutableStateFlow(Result.Nothing)
    private var resultFlow: MutableStateFlow<Result> = MutableStateFlow(Result.Nothing)
    private var historyFlow: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())

    override fun clear() {
        left.clear()
        right.clear()
        state = CalculationState.LEFT_PART_CLEAR
        changeOperation(operation = Operation.Nothing)
        calculationFlow.tryEmit(Result.Nothing)
        resultFlow.tryEmit(Result.Nothing)
    }

    override fun updateCalculation(value: String) {
        calculationFlow.tryEmit(Result.Success(value))
    }

    override fun updateResult(value: Result) {
        resultFlow.tryEmit(value)
    }

    override fun appendHistory(value: String) {
        historyFlow.value = historyFlow.value.toMutableList().also {
            it.add(value)
        }
    }

    override fun setLeftPartDot() {
        left.setDotOnEnd()
    }

    override fun setRightPartDot() {
        right.setDotOnEnd()
    }

    override fun changeLeftPart(value: String) {
        state = CalculationState.LEFT_PART_PRESENT
        left.update(value)
    }

    override fun changeRightPart(value: String) {
        state = CalculationState.RIGHT_PART_PRESENT
        right.update(value)
    }

    override fun compareCurrentState(with: CalculationState): Boolean {
        return state == with
    }

    override fun changeOperation(operation: Operation) {
        if (operation != Operation.Nothing) {
            this.state = CalculationState.OPERATOR_PRESENT
        }
        this.operation = operation
    }

    override fun getLeftPart(): String {
        return left.getValueString()
    }

    override fun getRightPart(): String {
        return right.getValueString()
    }

    override fun getOperation(): Operation {
        return operation
    }

    override fun calculate(): BigDecimal {
        return operation.calculate(left, right).apply {
            state = CalculationState.SHOWING_RESULT
        }
    }

    override fun calculationFlow(): Flow<Result> = calculationFlow.asStateFlow()

    override fun resultFlow(): Flow<Result> = resultFlow.asStateFlow()

    override fun historyFlow(): Flow<List<String>> = historyFlow.asStateFlow()
}
