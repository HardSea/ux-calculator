package com.hillywave.uxcalculator.data

import java.math.BigDecimal
import java.math.BigInteger
import javax.inject.Inject

interface Part {

	fun isEmpty(): Boolean

	fun clear()

	fun update(value: String)

	fun append(value: String)

	fun getValue(): BigDecimal

	fun getValueString(): String

	class Base @Inject constructor() : Part {
		private var value: BigDecimal = BigDecimal.ZERO

		override fun isEmpty(): Boolean {
			return value == BigDecimal.ZERO
		}

		override fun clear() {
			value = BigDecimal.ZERO
		}

		override fun update(value: String) {
			this.value = BigDecimal(value)
		}

		override fun append(value: String) {
			if (isEmpty()) {
				this.value = BigDecimal(value)
			} else {
				this.value = BigDecimal(this.value.toString() + value)
			}
		}

		override fun getValue(): BigDecimal {
			return value
		}

		override fun getValueString(): String {
			return value.toString()
		}
	}
}
