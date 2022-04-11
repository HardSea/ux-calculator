package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.MainRepository
import javax.inject.Inject

interface HistoryController {

	fun appendHistoryWithCalculationResult(value: String)

	class Base @Inject constructor(
		private val repository: MainRepository,
		private val historyMapper: HistoryValueMapper,
	) : HistoryController {
		override fun appendHistoryWithCalculationResult(value: String) {
			with(repository) {
				val leftString = getLeftPart()
				val rightString = getRightPart()
				appendHistory(
					historyMapper(
						left = if (leftString.last() == '.') leftString.dropLast(1) else leftString,
						operation = getOperation().toString(),
						right = if (rightString.last() == '.') rightString.dropLast(1) else rightString,
						result = value
					)
				)
			}
		}
	}
}
