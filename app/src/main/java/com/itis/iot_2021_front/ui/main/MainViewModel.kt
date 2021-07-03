package com.itis.iot_2021_front.ui.main

import androidx.lifecycle.viewModelScope
import com.itis.iot_2021_front.data.repository.IMainRepository
import com.itis.iot_2021_front.ui.base.BaseViewModel
import kotlinx.coroutines.*

class MainViewModel(private val repository: IMainRepository) :
    BaseViewModel<MainViewState, MainEvent, MainEffect>() {

    override fun createInitialState(): MainViewState = MainViewState.Idle

    override fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.InitEvent -> subscribeToAirDetector()
        }
    }

    private fun subscribeToAirDetector() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                setState { MainViewState.Loading }
                while (isActive) {
                    val data = repository.getMockAirSensorData()
                    setState { MainViewState.Success(data.eco2, data.tvoc) }
                    delay(500L)
                }
            } catch (ex: Exception) {
                setState { MainViewState.Error }
                setEffect { MainEffect.ErrorMessage(ex.localizedMessage) }
            }
        }
    }
}