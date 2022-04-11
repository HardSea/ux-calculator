package com.hillywave.uxcalculator.data

import java.math.BigDecimal
import javax.inject.Inject

interface Part : DotModel {

	fun isEmpty(): Boolean

	fun clear()

	fun update(value: String)

	fun getValue(): BigDecimal

	fun getValueString(): String

	class Base @Inject constructor() : Part {
		private var value: BigDecimal = BigDecimal.ZERO
		private var isDotPresent: Boolean = false

		override fun isEmpty(): Boolean {
			return value == BigDecimal.ZERO
		}

		override fun clear() {
			value = BigDecimal.ZERO
			isDotPresent = false
		}

		override fun update(value: String) {
			this.value = BigDecimal(value)
			isDotPresent = false
		}

		override fun getValue(): BigDecimal {
			return value
		}

		override fun getValueString(): String {
			return if (isDotPresent) {
				"$value."
			} else {
				value.toString()
			}
		}

		override fun setDot() {
			isDotPresent = true
		}

		override fun deleteDot() {
			//TODO("Not yet implemented")
		}
	}
}
