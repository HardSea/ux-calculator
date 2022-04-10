package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.model.HistoryDomainModel
import javax.inject.Inject

class HistoryListMapper @Inject constructor() {

	operator fun invoke(input: List<HistoryDomainModel>): List<String> {
		return input.map { domainModel ->
			buildString {
				append(domainModel.left.getValueString())
				append(" ")
				append(domainModel.operation)
				append(" ")
				append(domainModel.right.getValueString())
				append(" = ")
				append(domainModel.result)
			}
		}
	}
}