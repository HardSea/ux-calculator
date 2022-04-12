package com.hillywave.uxcalculator.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseViewModel<out S : UiState, in E : UiEvent> : ViewModel() {
	abstract val state: Flow<S>
}
