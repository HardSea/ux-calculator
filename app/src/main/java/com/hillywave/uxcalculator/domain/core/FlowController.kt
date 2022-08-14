package com.hillywave.uxcalculator.domain.core

import kotlinx.coroutines.flow.Flow

interface FlowController {

    fun calculationFlow(): Flow<Result>

    fun resultFlow(): Flow<Result>

    fun historyFlow(): Flow<List<String>>
}
