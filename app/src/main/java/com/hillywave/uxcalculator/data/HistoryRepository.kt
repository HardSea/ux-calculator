package com.hillywave.uxcalculator.data

interface HistoryRepository {

	fun appendHistory(value: String)
}
