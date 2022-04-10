package com.hillywave.uxcalculator.data

import com.hillywave.uxcalculator.domain.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.math.BigDecimal
import javax.inject.Inject
import javax.inject.Singleton

interface MainRepository : EditRepository, FlowRepository {

	fun compareCurrentState(other: CalculationState): Boolean

	fun getLeftPart(): String

	fun getRightPart(): String

	fun getOperation(): Operation

	fun calculate(): BigDecimal

	class Base @Inject constructor(private val left: Part, private val right: Part) : MainRepository {

		private var state: CalculationState = CalculationState.LEFT_PART_CLEAR
		private var operation: Operation = Operation.Nothing

		private var calculationFlow: MutableStateFlow<Result> = MutableStateFlow(Result.Nothing)
		private var _calculationFlow = calculationFlow.asStateFlow()

		private var resultFlow: MutableStateFlow<Result> = MutableStateFlow(Result.Nothing)
		private var _resultFlow = resultFlow.asStateFlow()

		override fun clear() {
			left.clear()
			right.clear()
			state = CalculationState.LEFT_PART_CLEAR
			changeOperation(operation = Operation.Nothing)
			calculationFlow.tryEmit(Result.Nothing)
			resultFlow.tryEmit(Result.Nothing)
		}

		override fun updateCalculation(value: String) {
			calculationFlow.tryEmit(Result.Success(value))
		}

		override fun updateResult(value: Result) {
			resultFlow.tryEmit(value)
		}

		override fun changeLeftPart(value: String) {
			state = CalculationState.LEFT_PART_PRESENT
			left.update(value)
		}

		override fun changeRightPart(value: String) {
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

		override fun calculate(): BigDecimal {
			return operation.calculate(left, right).apply {
				state = CalculationState.SHOWING_RESULT
			}
		}

		override fun calculationFlow(): Flow<Result> = _calculationFlow

		override fun resultFlow(): Flow<Result> = _resultFlow
	}
}
