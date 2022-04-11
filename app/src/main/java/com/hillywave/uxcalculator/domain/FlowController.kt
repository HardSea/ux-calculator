package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.FlowRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FlowController {

	fun calculationFlow(): Flow<Result>

	fun resultFlow(): Flow<Result>

	fun historyFlow(): Flow<List<String>>

	class Base @Inject constructor(private val flowRepository: FlowRepository) : FlowController {

		override fun calculationFlow(): Flow<Result> = flowRepository.calculationFlow()

		override fun resultFlow(): Flow<Result> = flowRepository.resultFlow()

		override fun historyFlow(): Flow<List<String>> = flowRepository.historyFlow()
	}
}
