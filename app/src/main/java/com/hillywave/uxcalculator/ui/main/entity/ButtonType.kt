package com.hillywave.uxcalculator.ui.main.entity

import androidx.annotation.RawRes
import com.hillywave.uxcalculator.R

interface ButtonType {
	val text: String
	val soundClickRes: Int

	enum class Numbers(override val text: String, @RawRes override val soundClickRes: Int = R.raw.click_button) : ButtonType {
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

	enum class Operator(override val text: String, @RawRes override val soundClickRes: Int = R.raw.click_button) : ButtonType {
		PLUS("+"),
		MINUS("-"),

		// TODO BRACKETS("()"),
		// TODO PERCENT("%"),
		DIVIDE("÷"),
		MULTIPLY("×"),
		EMPTY("")
	}

	enum class Operation(override val text: String, @RawRes override val soundClickRes: Int) : ButtonType {
		CLEAR("C", R.raw.click_operation),
		CALCULATE("=", R.raw.click_operation2)
	}

	enum class Other(override val text: String, @RawRes override val soundClickRes: Int = R.raw.click_button) : ButtonType {
		DOT(".")
	}
}
