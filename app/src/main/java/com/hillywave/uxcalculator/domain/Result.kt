package com.hillywave.uxcalculator.domain

sealed class Result {
	data class Success(val result: String) : Result()
	data class Error(val message: Exception) : Result()
	object Nothing : Result()
}