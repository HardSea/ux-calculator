package com.hillywave.uxcalculator.domain

import javax.inject.Inject

class HistoryListMapper @Inject constructor() {
	operator fun invoke(left: String, operation: String, right: String, result: String): String {
		return buildString {
			append(left)
			append(" ")
			append(operation)
			append(" ")
			append(right)
			append(" = ")
			append(result)
		}
	}
}
