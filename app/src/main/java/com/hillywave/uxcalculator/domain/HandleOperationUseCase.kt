package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.MainRepository
import com.hillywave.uxcalculator.data.Operation
import javax.inject.Inject

class HandleOperationUseCase @Inject constructor(private val repository: MainRepository) : HandleOperation {
	override fun handle(operation: Operation) = with(repository) {
		if (compareCurrentState(CalculationState.LEFT_PART_CLEAR)) {
			return
		}

		if (compareCurrentState(CalculationState.LEFT_PART_PRESENT) || compareCurrentState(CalculationState.OPERATOR_PRESENT)) {
			changeOperation(operation)
			updateCalculation(repository.getLeftPart() + repository.getOperation())
			return
		}
	}
}
