package com.itis.iot_2021_front.ui.main

import androidx.lifecycle.viewModelScope
import com.itis.iot_2021_front.data.repository.IMainInteractor
import com.itis.iot_2021_front.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(private val interactor: IMainInteractor) :
    BaseViewModel<MainViewState, MainEvent, MainEffect>() {

    override fun createInitialState(): MainViewState = MainViewState.Idle

    override fun handleEvent(event: MainEvent) {
        when (event) {
            MainEvent.InitEvent -> subscribeToSocketEvents()
        }
    }

    private fun subscribeToSocketEvents() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                setState { MainViewState.Loading }
                interactor.stopSocket()
                interactor.startSocket().consumeEach {
                    if (it.exception == null) {
                        setState { MainViewState.Success(it.eco2, it.tvoc) }
                    } else {
                        setState { MainViewState.Error }
                    }
                }
//                delay(3000L)
//                setState { MainViewState.Success(400, 102) }
//                delay(3000L)
//                setState { MainViewState.Success(600, 100) }
//                delay(3000L)
//                setState { MainViewState.Success(700, 101) }
//                delay(3000L)
//                setState { MainViewState.Success(1000, 99) }
//                delay(3000L)
//                setState { MainViewState.Success(1500, 103) }
//                delay(3000L)
//                setState { MainViewState.Success(2000, 98) }
            } catch (ex: Exception) {
                setState { MainViewState.Error }
            }
        }
    }

    override fun onCleared() {
        interactor.stopSocket()
        super.onCleared()
    }
}