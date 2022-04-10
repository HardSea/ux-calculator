package com.hillywave.uxcalculator.data

import java.math.BigDecimal
import java.math.MathContext

sealed class Operation {
	abstract fun calculate(left: Part, right: Part): BigDecimal

	object Plus : Operation() {
		override fun calculate(left: Part, right: Part): BigDecimal {
			return left.getValue().add(right.getValue())
		}

		override fun toString(): String {
			return "+"
		}
	}

	object Minus : Operation() {
		override fun calculate(left: Part, right: Part): BigDecimal {
			return left.getValue().minus(right.getValue())
		}

		override fun toString(): String {
			return "-"
		}
	}

	object Divide : Operation() {
		override fun calculate(left: Part, right: Part): BigDecimal {
			return left.getValue().divide(right.getValue(), MathContext.DECIMAL32)
		}

		override fun toString(): String {
			return "÷"
		}
	}

	object Multiply : Operation() {
		override fun calculate(left: Part, right: Part): BigDecimal {
			return left.getValue().multiply(right.getValue())
		}

		override fun toString(): String {
			return "×"
		}
	}

	object Nothing : Operation() {
		override fun calculate(left: Part, right: Part): BigDecimal {
			return BigDecimal.ZERO
		}

		override fun toString(): String {
			return "0"
		}
	}
}
