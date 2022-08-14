package com.hillywave.uxcalculator.data.core

import com.hillywave.uxcalculator.domain.core.Result
import kotlinx.coroutines.flow.Flow

interface FlowRepository {

	fun calculationFlow(): Flow<Result>

	fun resultFlow(): Flow<Result>

	fun historyFlow(): Flow<List<String>>
}
