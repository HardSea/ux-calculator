package com.hillywave.uxcalculator.domain

import com.hillywave.uxcalculator.data.core.MainRepository
import com.hillywave.uxcalculator.domain.core.HistoryController
import javax.inject.Inject

class BaseHistoryController @Inject constructor(
    private val repository: MainRepository,
    private val historyMapper: HistoryValueMapper,
) : HistoryController {
    override fun appendHistory(value: String) {
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
