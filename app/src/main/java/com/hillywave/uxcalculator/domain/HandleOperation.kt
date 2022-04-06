package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.Operation

interface HandleOperation {
	fun handle(operation: Operation): Result
}
