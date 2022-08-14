package com.hillywave.uxcalculator.domain.usecase

import com.hillywave.uxcalculator.data.CalculationState
import com.hillywave.uxcalculator.data.Operation
import com.hillywave.uxcalculator.data.core.MainRepository
import javax.inject.Inject

class HandleOperationUseCase @Inject constructor(private val repository: MainRepository) {
    operator fun invoke(operation: Operation) = with(repository) {
        if (compareCurrentState(CalculationState.LEFT_PART_CLEAR) || compareCurrentState(CalculationState.SHOWING_RESULT)) {
            return
        }

        if (compareCurrentState(CalculationState.LEFT_PART_PRESENT) || compareCurrentState(CalculationState.OPERATOR_PRESENT)) {
            changeOperation(operation)
            updateCalculation(repository.getLeftPart() + repository.getOperation())
            return
        }
    }
}
