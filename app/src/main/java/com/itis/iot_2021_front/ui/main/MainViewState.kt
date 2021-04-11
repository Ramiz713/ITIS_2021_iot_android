package com.itis.iot_2021_front.ui.main

import com.itis.iot_2021_front.model.UiEffect
import com.itis.iot_2021_front.model.UiEvent
import com.itis.iot_2021_front.model.UiState

sealed class MainViewState : UiState {
    object Idle : MainViewState()
    object Loading : MainViewState()
    data class Success(val concentration: Int, val tvoc: Int) : MainViewState()
    object Error : MainViewState()
}

sealed class MainEvent : UiEvent {
    object InitEvent : MainEvent()
}

sealed class MainEffect : UiEffect {
    object ErrorMessage : MainEffect()
}