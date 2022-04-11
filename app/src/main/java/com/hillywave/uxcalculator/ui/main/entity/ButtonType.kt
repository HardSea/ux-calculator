package com.hillywave.uxcalculator.ui.main.entity

interface ButtonType {
	val text: String

	enum class Numbers(override val text: String) : ButtonType {
		ONE("1"),
		TWO("2"),
		THREE("3"),
		FOUR("4"),
		FIVE("5"),
		SIX("6"),
		SEVEN("7"),
		EIGHT("8"),
		NINE("9"),
		ZERO("0"),
		EMPTY("")
	}

	enum class Operator(override val text: String) : ButtonType {
		PLUS("+"),
		MINUS("-"),

		// TODO BRACKETS("()"),
		// TODO PERCENT("%"),
		DIVIDE("÷"),
		MULTIPLY("×"),
		EMPTY("")
	}

	enum class Operation(override val text: String) : ButtonType {
		CLEAR("C"),
		CALCULATE("=")
	}

	enum class Other(override val text: String) : ButtonType {
		DOT(".")
	}
}
