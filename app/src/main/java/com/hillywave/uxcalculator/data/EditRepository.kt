package com.hillywave.uxcalculator.data

import com.hillywave.uxcalculator.domain.Result

interface EditRepository {

	fun clear()

	fun changeLeftPart(value: String)

	fun changeRightPart(value: String)

	fun changeOperation(operation: Operation)

	fun updateCalculation(value: String)

	fun updateResult(value: Result)
}
