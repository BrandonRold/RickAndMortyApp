package com.brs.rickyandmorthy.presentation.common.components.topbar

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow


class TopBarViewModel : ViewModel() {
    private val _state = MutableStateFlow(TopBarState())
    val state: StateFlow<TopBarState> = _state.asStateFlow()

    fun updateState(newState: TopBarState) {
        _state.value = newState
    }

    fun setTitle(title: String) {
        _state.value = _state.value.copy(title = title)
    }

    fun setSubtitle(subtitle: String) {
        _state.value = _state.value.copy(subtitle = subtitle)
    }
}
