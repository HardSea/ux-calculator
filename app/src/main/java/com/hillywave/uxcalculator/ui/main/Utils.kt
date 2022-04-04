package com.hillywave.uxcalculator.ui.main

import com.hillywave.uxcalculator.ui.main.entity.ButtonType

fun calculatorButtons(): List<ButtonType> = listOf(
	ButtonType.Operation.CLEAR,
	ButtonType.Operator.EMPTY,
	ButtonType.Operator.EMPTY,
	ButtonType.Operator.DIVIDE,
	ButtonType.Numbers.SEVEN,
	ButtonType.Numbers.EIGHT,
	ButtonType.Numbers.NINE,
	ButtonType.Operator.MULTIPLY,
	ButtonType.Numbers.FOUR,
	ButtonType.Numbers.FIVE,
	ButtonType.Numbers.SIX,
	ButtonType.Operator.MINUS,
	ButtonType.Numbers.ONE,
	ButtonType.Numbers.TWO,
	ButtonType.Numbers.THREE,
	ButtonType.Operator.PLUS,
	ButtonType.Numbers.EMPTY,
	ButtonType.Numbers.ZERO,
	ButtonType.Numbers.EMPTY,
	ButtonType.Operation.CALCULATE
)
