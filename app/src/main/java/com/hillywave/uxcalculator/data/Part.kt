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
        private var value: BigDecimal? = null
        private var isDotOnEnd: Boolean = false

        override fun isEmpty(): Boolean {
            return value == null
        }

        override fun clear() {
            value = null
            isDotOnEnd = false
        }

        override fun update(value: String) {
            this.value = BigDecimal(value)
            isDotOnEnd = false
        }

        override fun getValue(): BigDecimal {
            return value ?: BigDecimal.ZERO
        }

        override fun getValueString(): String {
            return if (isDotOnEnd) {
                "$value."
            } else {
                if (isEmpty()) {
                    ""
                } else {
                    value.toString()
                }
            }
        }

        override fun setDotOnEnd() {
            if (!getValueString().contains('.')) {
                isDotOnEnd = true
            }
        }
    }
}
