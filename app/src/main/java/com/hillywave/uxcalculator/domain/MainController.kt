package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Operation
import javax.inject.Inject

interface MainController {

	fun plus(): Result

	fun minus(): Result

	fun divide(): Result

	fun multiply(): Result

	fun calculate(): Result

	fun handleNumber(value: String): Result

	class Base @Inject constructor(private val repository: MainRepository) : MainController {

		private val handleOperationUseCase = HandleOperationUseCase(repository)

		override fun plus(): Result {
			return handleOperationUseCase.handle(Operation.Plus)
		}

		override fun minus(): Result {
			return handleOperationUseCase.handle(Operation.Minus)
		}

		override fun divide(): Result {
			return handleOperationUseCase.handle(Operation.Divide)
		}

		override fun multiply(): Result {
			return handleOperationUseCase.handle(Operation.Multiply)
		}

		override fun calculate(): Result {
			if (repository.isLeftPartEmpty() || repository.isRightPartEmpty()) {
				return Result.Nothing
			}
			return try {
				Result.Success(repository.calculate().toString())
			} catch (e: Exception) {
				Result.Error(e)
			}
		}

		override fun handleNumber(value: String): Result {
			with(repository) {
				if (compareCurrentState(CalculationState.LEFT_PART_CLEAR) || compareCurrentState(CalculationState.LEFT_PART_PRESENT)) {
					repository.appendLeftPart(value)
					return Result.Success(getLeftPart())
				}
				repository.updateRightPart(value)
				return Result.Success(getLeftPart() + getOperation() + getRightPart())
			}
		}
	}
}
