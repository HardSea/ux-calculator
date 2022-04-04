package com.hillywave.uxcalculator.data

import java.math.BigInteger

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

	class Base(private val left: Part, private val right: Part) : MainRepository {

		private var state: CalculationState = CalculationState.LEFT_PART_CLEAR
		private var operation: Operation = Operation.Nothing

		override fun clear() {
			left.clear()
			right.clear()

			state = CalculationState.LEFT_PART_CLEAR
		}

		override fun updateLeftPart(value: String) {
			left.update(value)
		}

		override fun updateRightPart(value: String) {
			right.update(value)
		}

		override fun compareCurrentState(other: CalculationState): Boolean {
			return state == other
		}

		override fun changeOperation(operation: Operation) {
			this.operation = operation
		}

		override fun getLeftPart(): String {
			return left.toString()
		}

		override fun getRightPart(): String {
			return right.toString()
		}

		override fun getOperation(): String {
			return operation.toString()
		}

		override fun calculate(): BigInteger {
			return operation.calculate(left, right)
		}

		override fun isLeftPartEmpty(): Boolean {
			return left.isEmpty()
		}

		override fun isRightPartEmpty(): Boolean {
			return right.isEmpty()
		}
	}
}
