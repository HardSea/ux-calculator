package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Operation
import kotlinx.coroutines.flow.Flow
import java.math.BigInteger
import javax.inject.Inject

interface MainController {

	fun calculationFlow(): Flow<String>

	fun resultFlow(): Flow<String>

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
						updateLeftPart(getLeftPart().dropLast(1))
					} else {
						updateLeftPart(BigInteger.ZERO.toString())
					}
					updateCalculation(getLeftPart())
					return
				}
				if (compareCurrentState(CalculationState.OPERATOR_PRESENT)) {
					changeOperation(Operation.Nothing)
					updateLeftPart(getLeftPart())
					updateCalculation(getLeftPart())
					return
				}
				if (compareCurrentState(CalculationState.RIGHT_PART_PRESENT) || compareCurrentState(CalculationState.SHOWING_RESULT)) {
					if (getRightPart().length > 1) {
						updateRightPart(getRightPart().dropLast(1))
						updateCalculation(getLeftPart() + getOperation() + getRightPart())
					} else {
						updateRightPart(BigInteger.ZERO.toString())
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
						repository.calculate().toString()
					} catch (e: Exception) {
						"Error"
					}
				)
			}
		}

		override fun calculationFlow(): Flow<String> = repository.calculationFlow()

		override fun resultFlow(): Flow<String> = repository.resultFlow()

		override fun handleNumber(value: String) {
			with(repository) {
				if (compareCurrentState(CalculationState.SHOWING_RESULT)) {
					clear()
				}
				if (compareCurrentState(CalculationState.LEFT_PART_CLEAR) || compareCurrentState(CalculationState.LEFT_PART_PRESENT)) {
					updateLeftPart(getLeftPart() + value)
					updateCalculation(getLeftPart())
					return
				}
				updateRightPart(getRightPart() + value)
				updateCalculation(getLeftPart() + getOperation() + getRightPart())
			}
		}
	}
}
