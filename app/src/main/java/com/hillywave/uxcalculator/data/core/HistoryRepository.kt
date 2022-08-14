package com.hillywave.uxcalculator.data.core

interface HistoryRepository {
	fun appendHistory(value: String)
}
