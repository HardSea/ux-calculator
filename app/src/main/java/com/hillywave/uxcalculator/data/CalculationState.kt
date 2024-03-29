package com.hillywave.uxcalculator.data

enum class CalculationState {
    LEFT_PART_CLEAR,
    LEFT_PART_PRESENT,
    LEFT_PART_PRESENT_WITH_DOT,
    OPERATOR_PRESENT,
    RIGHT_PART_PRESENT,
    RIGHT_PART_PRESENT_WITH_DOT,
    SHOWING_RESULT
}
