package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Operation

class HandleOperationUseCase(private val repository: MainRepository) : HandleOperation {
	override fun handle(operation: Operation): Result {
		if (repository.isLeftPartEmpty()) {
			return Result.Nothing
		}
		if (repository.compareCurrentState(CalculationState.DONE)) {
			return Result.Nothing
		}
		repository.changeOperation(operation)
		return Result.Success(repository.getLeftPart() + repository.getOperation())
	}
}