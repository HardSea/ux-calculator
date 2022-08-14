package com.hillywave.uxcalculator.data.core

import java.math.BigDecimal

interface Part {

    fun isEmpty(): Boolean

    fun clear()

    fun update(value: String)

    fun getValue(): BigDecimal

    fun getValueString(): String

    fun setDotOnEnd()
}
