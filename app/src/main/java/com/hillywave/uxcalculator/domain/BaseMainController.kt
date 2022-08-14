package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.R
import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Operation
import com.hillywave.uxcalculator.di.NumberLengthLimit
import com.hillywave.uxcalculator.domain.core.MainController
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

class BaseMainController @Inject constructor(
    private val repository: MainRepository,
    private val handleOperationUseCase: HandleOperationUseCase,
    private val historyController: HistoryController,
    @NumberLengthLimit private val numberLengthLimit: Int,
) : MainController {

    override fun clear() = repository.clear()

    override fun delete() = with(repository) {
        when {
            isLeftPartPresent() -> handleDeleteLeftPart()
            isOperatorPresent() -> handleDeleteOperator()
            isRightPartPresent() || isCurrentShowingResult() -> handleDeleteRightPartOrShowingResult()
            else -> this@BaseMainController.clear()
        }
    }

    private fun MainRepository.handleDeleteLeftPart() {
        if (getLeftPart().length > 1) {
            getLeftPart().dropLast(1).also {
                if (it.last() == '.') {
                    changeLeftPart(it.dropLast(1))
                    setLeftPartDot()
                } else {
                    changeLeftPart(it)
                }
            }
        } else {
            changeLeftPart(BigInteger.ZERO.toString())
        }
        updateCalculation(getLeftPart())
    }

    private fun MainRepository.handleDeleteOperator() {
        changeOperation(Operation.Nothing)
        changeLeftPart(getLeftPart())
        updateCalculation(getLeftPart())
    }

    private fun MainRepository.handleDeleteRightPartOrShowingResult() {
        if (getRightPart().length > 1) {
            getRightPart().dropLast(1).also {
                if (it.last() == '.') {
                    changeRightPart(it.dropLast(1))
                    setRightPartDot()
                } else {
                    changeRightPart(it)
                }
            }
        } else {
            changeRightPart(BigInteger.ZERO.toString())
            changeOperation(getOperation())
            updateCalculation(getLeftPart() + getOperation())
            return
        }
        updateCalculation(getLeftPart() + getOperation() + getRightPart())
    }

    private fun isLeftPartPresent() = repository.compareCurrentState(CalculationState.LEFT_PART_PRESENT)

    private fun isLeftPartClear() = repository.compareCurrentState(CalculationState.LEFT_PART_CLEAR)

    private fun isRightPartPresent() = repository.compareCurrentState(CalculationState.RIGHT_PART_PRESENT)

    private fun isOperatorPresent() = repository.compareCurrentState(CalculationState.OPERATOR_PRESENT)

    private fun isCurrentShowingResult() = repository.compareCurrentState(CalculationState.SHOWING_RESULT)

    override fun plus() = handleOperationUseCase(Operation.Plus)

    override fun minus() = handleOperationUseCase(Operation.Minus)

    override fun divide() = handleOperationUseCase(Operation.Divide)

    override fun multiply() = handleOperationUseCase(Operation.Multiply)

    override fun calculate() {
        with(repository) {
            if (isRightPartPresent()) {
                updateResult(
                    try {
                        Result.Success(calculate().toString()).also {
                            historyController.appendHistoryWithCalculationResult(it.value)
                        }
                    } catch (e: Exception) {
                        Result.Error(R.string.standard_error)
                    }
                )
            }
        }
    }

    override fun handleNumber(value: String) {
        with(repository) {
            if (isCurrentShowingResult()) {
                clear()
            }
            if (isLeftPartClear() || isLeftPartPresent()) {
                if (getLeftPart().length >= numberLengthLimit) return
                changeLeftPart(getLeftPart() + value)
                updateCalculation(getLeftPart() + getOperation() + getRightPart())
                return
            }
            if (getRightPart().length >= numberLengthLimit) return
            changeRightPart(getRightPart() + value)
            updateCalculation(getLeftPart() + getOperation() + getRightPart())
        }
    }

    override fun handleDot() {
        with(repository) {
            if (compareCurrentState(CalculationState.SHOWING_RESULT)) {
                clear()
            }
            if (isLeftPartClear()) {
                changeLeftPart(BigDecimal.ZERO.toString())
                setLeftPartDot()
                updateCalculation(getLeftPart())
                return
            }
            if (isLeftPartPresent()) {
                setLeftPartDot()
                updateCalculation(getLeftPart())
                return
            }
            setRightPartDot()
            if (isOperatorPresent()) {
                changeRightPart(BigDecimal.ZERO.toString())
            }
            updateCalculation(getLeftPart() + getOperation() + getRightPart())
        }
    }
}
