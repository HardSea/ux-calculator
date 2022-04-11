package com.hillywave.uxcalculator.data

import com.hillywave.uxcalculator.domain.Result
import kotlinx.coroutines.flow.Flow

interface FlowRepository {

	fun calculationFlow(): Flow<Result>

	fun resultFlow(): Flow<Result>

	fun historyFlow(): Flow<List<String>>
}
