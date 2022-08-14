package com.hillywave.uxcalculator.domain.core

interface MainController {

    fun clear()

    fun delete()

    fun plus()

    fun minus()

    fun divide()

    fun multiply()

    fun calculate()

    fun handleNumber(value: String)

    fun handleDot()
}
