package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Operation
import javax.inject.Inject
import javax.inject.Singleton

class HandleOperationUseCase @Inject constructor(private val repository: MainRepository) : HandleOperation {
	override fun handle(operation: Operation): Result {
		if (repository.compareCurrentState(CalculationState.LEFT_PART_CLEAR)) {
			return Result.Nothing
		}
		if (repository.compareCurrentState(CalculationState.LEFT_PART_PRESENT)) {
			repository.changeOperation(operation)
			return Result.Success(repository.getLeftPart() + repository.getOperation())
		}

		if (repository.compareCurrentState(CalculationState.OPERATOR_PRESENT)) {
			repository.changeOperation(operation)
			return Result.Success(repository.getLeftPart() + repository.getOperation())
		}

		return Result.Nothing
	}
}
