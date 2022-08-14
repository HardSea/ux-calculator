package com.hillywave.uxcalculator.data.core

import com.hillywave.uxcalculator.data.Operation
import com.hillywave.uxcalculator.domain.core.Result

interface EditRepository {

	fun clear()

	fun changeLeftPart(value: String)

	fun changeRightPart(value: String)

	fun changeOperation(operation: Operation)

	fun updateCalculation(value: String)

	fun updateResult(value: Result)

	fun setLeftPartDot()

	fun setRightPartDot()
}
