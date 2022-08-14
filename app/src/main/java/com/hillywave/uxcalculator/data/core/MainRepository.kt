package com.hillywave.uxcalculator.data.core

import com.hillywave.uxcalculator.data.*
import java.math.BigDecimal

interface MainRepository : EditRepository, FlowRepository, HistoryRepository {

    fun compareCurrentState(with: CalculationState): Boolean

    fun getLeftPart(): String

    fun getRightPart(): String

    fun getOperation(): Operation

    fun calculate(): BigDecimal
}
