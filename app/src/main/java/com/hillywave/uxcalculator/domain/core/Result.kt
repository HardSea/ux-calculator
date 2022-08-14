package com.hillywave.uxcalculator.domain.core

import androidx.annotation.StringRes

sealed class Result {
    data class Success(val value: String) : Result()
    data class Error(@StringRes val messageRes: Int) : Result()
    object Nothing : Result()
}
