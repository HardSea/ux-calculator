package com.hillywave.uxcalculator.data

import java.math.BigInteger
import javax.inject.Inject

interface Part {

	fun isEmpty(): Boolean

	fun clear()

	fun update(value: String)

	fun append(value: String)

	fun getValue(): BigInteger

	fun getValueString(): String

	class Base @Inject constructor() : Part {
		private var value: BigInteger = BigInteger.ZERO

		override fun isEmpty(): Boolean {
			return value == BigInteger.ZERO
		}

		override fun clear() {
			value = BigInteger.ZERO
		}

		override fun update(value: String) {
			this.value = BigInteger(value)
		}

		override fun append(value: String) {
			if (isEmpty()) {
				this.value = BigInteger(value)
			} else {
				this.value = BigInteger(this.value.toString() + value)
			}
		}

		override fun getValue(): BigInteger {
			return value
		}

		override fun getValueString(): String {
			return value.toString()
		}
	}
}
