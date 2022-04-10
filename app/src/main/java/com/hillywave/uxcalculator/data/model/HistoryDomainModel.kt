package com.hillywave.uxcalculator.data.model

import com.hillywave.uxcalculator.data.Operation
import com.hillywave.uxcalculator.data.Part

data class HistoryDomainModel(
	val left: Part,
	val operation: Operation,
	val right: Part,
	val result: String,
)

