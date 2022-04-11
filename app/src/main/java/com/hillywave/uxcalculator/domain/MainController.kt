package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.R
import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Operation
import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

interface MainController {

	fun clear()

	fun delete()

	fun plus()

	fun minus()

	fun divide()

	fun multiply()

	fun calculate()

	fun handleNumber(value: String)

	fun handleDot()

	class Base @Inject constructor(
		private val repository: MainRepository,
		private val handleOperation: HandleOperation,
		private val historyController: HistoryController
	) : MainController {

		override fun clear() {
			repository.clear()
		}

		override fun delete() {
			with(repository) {
				if (compareCurrentState(CalculationState.LEFT_PART_PRESENT)) {
					if (getLeftPart().length > 1) {
						changeLeftPart(getLeftPart().dropLast(1))
					} else {
						changeLeftPart(BigInteger.ZERO.toString())
					}
					updateCalculation(getLeftPart())
					return
				}
				if (compareCurrentState(CalculationState.OPERATOR_PRESENT)) {
					changeOperation(Operation.Nothing)
					changeLeftPart(getLeftPart())
					updateCalculation(getLeftPart())
					return
				}
				if (compareCurrentState(CalculationState.RIGHT_PART_PRESENT) || compareCurrentState(CalculationState.SHOWING_RESULT)) {
					if (getRightPart().length > 1) {
						changeRightPart(getRightPart().dropLast(1))
						updateCalculation(getLeftPart() + getOperation() + getRightPart())
					} else {
						changeRightPart(BigInteger.ZERO.toString())
						changeOperation(getOperation())
						updateCalculation(getLeftPart() + getOperation())
					}
					return
				}
				this@Base.clear()
			}
		}

		override fun plus() {
			handleOperation.handle(Operation.Plus)
		}

		override fun minus() {
			handleOperation.handle(Operation.Minus)
		}

		override fun divide() {
			handleOperation.handle(Operation.Divide)
		}

		override fun multiply() {
			handleOperation.handle(Operation.Multiply)
		}

		override fun calculate() {
			with(repository) {
				if (compareCurrentState(CalculationState.RIGHT_PART_PRESENT)) {
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
				if (compareCurrentState(CalculationState.SHOWING_RESULT)) {
					clear()
				}
				if (compareCurrentState(CalculationState.LEFT_PART_CLEAR) || compareCurrentState(CalculationState.LEFT_PART_PRESENT)) {
					changeLeftPart(getLeftPart() + value)
					updateCalculation(getLeftPart())
					return
				}
				changeRightPart(getRightPart() + value)
				updateCalculation(getLeftPart() + getOperation() + getRightPart())
			}
		}

		override fun handleDot() {
			with(repository) {
				if (compareCurrentState(CalculationState.SHOWING_RESULT)) {
					clear()
				}
				setLeftPartDot()
				if (compareCurrentState(CalculationState.LEFT_PART_CLEAR)) {
					changeLeftPart(BigDecimal.ZERO.toString())
					updateCalculation(getLeftPart())
					return
				}
				if (compareCurrentState(CalculationState.LEFT_PART_PRESENT)) {
					updateCalculation(getLeftPart())
					return
				}
				setRightPartDot()
				if (compareCurrentState(CalculationState.OPERATOR_PRESENT)) {
					changeRightPart(BigDecimal.ZERO.toString())
				}
				updateCalculation(getLeftPart() + getOperation() + getRightPart())
			}
		}
	}
}
