package com.hillywave.uxcalculator.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class Reducer<S : UiState, E : UiEvent>(initialState: S) {

	private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
	val state: StateFlow<S> = _state.asStateFlow()

	fun sendEvent(event: E) {
		reduce(_state.value, event)
	}

	fun setState(newState: S) {
		_state.tryEmit(newState)
	}

	abstract fun reduce(oldState: S, event: E)
}
