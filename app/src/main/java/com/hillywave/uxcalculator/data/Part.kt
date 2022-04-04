package com.hillywave.uxcalculator.data

import java.math.BigInteger
import javax.inject.Inject
import javax.inject.Singleton

interface Part {

	fun isEmpty(): Boolean

	fun clear()

	fun update(value: String)

	fun value(): BigInteger

	@Singleton
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

		override fun value(): BigInteger {
			return value
		}
	}
}
