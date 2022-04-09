package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Operation
import javax.inject.Inject

interface MainController {
	fun clear(): Result

	fun delete(): Result

	fun plus(): Result

	fun minus(): Result

	fun divide(): Result

	fun multiply(): Result

	fun calculate(): Result

	fun handleNumber(value: String): Result

	class Base @Inject constructor(
		private val repository: MainRepository,
		private val handleOperation: HandleOperation,
	) : MainController {

		override fun clear(): Result {
			repository.clear()
			return Result.Success("")
		}

		override fun delete(): Result {
			with(repository) {
				if (compareCurrentState(CalculationState.LEFT_PART_PRESENT)) {
					updateLeftPart(getLeftPart().dropLast(1))
					return Result.Success(getLeftPart())
				}
				if (compareCurrentState(CalculationState.OPERATOR_PRESENT)) {
					changeOperation(Operation.Nothing)
					return Result.Success(getLeftPart())
				}
				if (compareCurrentState(CalculationState.RIGHT_PART_PRESENT)) {
					updateLeftPart(getRightPart().dropLast(1))
					return Result.Success(getLeftPart() + getOperation() + getRightPart())
				}
				return this@Base.clear()
			}
		}

		override fun plus(): Result {
			return handleOperation.handle(Operation.Plus)
		}

		override fun minus(): Result {
			return handleOperation.handle(Operation.Minus)
		}

		override fun divide(): Result {
			return handleOperation.handle(Operation.Divide)
		}

		override fun multiply(): Result {
			return handleOperation.handle(Operation.Multiply)
		}

		override fun calculate(): Result {
			with(repository) {
				if (isLeftPartEmpty() || isRightPartEmpty()) {
					return Result.Nothing
				}
				return try {
					Result.Success(calculate().toString())
				} catch (e: Exception) {
					Result.Error(e)
				}
			}
		}

		override fun handleNumber(value: String): Result {
			with(repository) {
				if (compareCurrentState(CalculationState.LEFT_PART_CLEAR) || compareCurrentState(CalculationState.LEFT_PART_PRESENT)) {
					updateLeftPart(getLeftPart() + value)
					return Result.Success(getLeftPart())
				}
				updateRightPart(getRightPart() + value)
				return Result.Success(getLeftPart() + getOperation() + getRightPart())
			}
		}
	}
}
