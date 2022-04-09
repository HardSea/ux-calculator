package com.hillywave.uxcalculator.data

import java.math.BigInteger
import javax.inject.Inject

interface MainRepository : Validation {

	fun clear()

	fun updateLeftPart(value: String)

	fun updateRightPart(value: String)

	fun compareCurrentState(other: CalculationState): Boolean

	fun changeOperation(operation: Operation)

	fun getLeftPart(): String

	fun getRightPart(): String

	fun getOperation(): String

	fun calculate(): BigInteger

	class Base @Inject constructor(private val left: Part, private val right: Part) : MainRepository {

		private var state: CalculationState = CalculationState.LEFT_PART_CLEAR
		private var operation: Operation = Operation.Nothing

		override fun clear() {
			left.clear()
			right.clear()
			state = CalculationState.LEFT_PART_CLEAR
			changeOperation(operation = Operation.Nothing)
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

		override fun getOperation(): String {
			return operation.toString()
		}

		override fun calculate(): BigInteger {
			return operation.calculate(left, right).apply {
				left.clear()
				right.clear()

				state = CalculationState.LEFT_PART_CLEAR
			}
		}

		override fun isLeftPartEmpty(): Boolean {
			return left.isEmpty()
		}

		override fun isRightPartEmpty(): Boolean {
			return right.isEmpty()
		}
	}
}
