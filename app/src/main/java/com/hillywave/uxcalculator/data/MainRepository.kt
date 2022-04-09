package com.hillywave.uxcalculator.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigInteger
import javax.inject.Inject

interface MainRepository {

	fun clear()

	fun updateLeftPart(value: String)

	fun updateRightPart(value: String)

	fun compareCurrentState(other: CalculationState): Boolean

	fun changeOperation(operation: Operation)

	fun getLeftPart(): String

	fun getRightPart(): String

	fun getOperation(): Operation

	fun calculate(): BigInteger

	fun calculationFlow(): Flow<String>

	fun resultFlow(): Flow<String>

	fun updateCalculation(value: String)

	fun updateResult(value: String)

	class Base @Inject constructor(private val left: Part, private val right: Part) : MainRepository {

		private var state: CalculationState = CalculationState.LEFT_PART_CLEAR
		private var operation: Operation = Operation.Nothing

		private var calculationFlow = MutableStateFlow("")
		private var _calculationFlow = calculationFlow.asStateFlow()

		private var resultFlow = MutableStateFlow("")
		private var _resultFlow = resultFlow.asStateFlow()

		override fun clear() {
			left.clear()
			right.clear()
			state = CalculationState.LEFT_PART_CLEAR
			changeOperation(operation = Operation.Nothing)
			calculationFlow.tryEmit("")
			resultFlow.tryEmit("")
		}

		override fun updateCalculation(value: String) {
			calculationFlow.tryEmit(value)
		}

		override fun updateResult(value: String) {
			resultFlow.tryEmit(value)
		}

		override fun updateLeftPart(value: String) {
			state = CalculationState.LEFT_PART_PRESENT
			left.update(value)
		}

		override fun updateRightPart(value: String) {
			state = CalculationState.RIGHT_PART_PRESENT
			right.update(value)
		}

		override fun compareCurrentState(other: CalculationState): Boolean {
			return state == other
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

		override fun calculate(): BigInteger {
			return operation.calculate(left, right).apply {
				state = CalculationState.SHOWING_RESULT
			}
		}

		override fun calculationFlow(): Flow<String> = _calculationFlow

		override fun resultFlow(): Flow<String> = _resultFlow
	}
}
