package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.R
import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Operation
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

	class Base @Inject constructor(
		private val repository: MainRepository,
		private val handleOperation: HandleOperation,
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
			if (repository.compareCurrentState(CalculationState.RIGHT_PART_PRESENT)) {
				repository.updateResult(
					try {
						Result.Success(repository.calculate().toString())
					} catch (e: Exception) {
						Result.Error(R.string.standard_error)
					}
				)
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
	}
}
