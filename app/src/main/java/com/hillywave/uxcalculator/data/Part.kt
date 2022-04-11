package com.hillywave.uxcalculator.data

import java.math.BigDecimal
import javax.inject.Inject

interface Part {

	fun isEmpty(): Boolean

	fun clear()

	fun update(value: String)

	fun getValue(): BigDecimal

	fun getValueString(): String

	fun setDotOnEnd()

	class Base @Inject constructor() : Part {
		private var value: BigDecimal = BigDecimal.ZERO
		private var isDotOnEnd: Boolean = false

		override fun isEmpty(): Boolean {
			return value == BigDecimal.ZERO
		}

		override fun clear() {
			value = BigDecimal.ZERO
			isDotOnEnd = false
		}

		override fun update(value: String) {
			this.value = BigDecimal(value)
			isDotOnEnd = false
		}

		override fun getValue(): BigDecimal {
			return value
		}

		override fun getValueString(): String {
			return if (isDotOnEnd) {
				"$value."
			} else {
				value.toString()
			}
		}

		override fun setDotOnEnd() {
			if (!getValueString().contains('.')) {
				isDotOnEnd = true
			}
		}
	}
}
