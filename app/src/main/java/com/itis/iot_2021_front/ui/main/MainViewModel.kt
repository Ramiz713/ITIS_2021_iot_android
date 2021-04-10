package com.itis.iot_2021_front.ui.main

import androidx.lifecycle.viewModelScope
import com.itis.iot_2021_front.data.repository.IMainInteractor
import com.itis.iot_2021_front.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
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
//                interactor.stopSocket()
//                interactor.startSocket().consumeEach {
//                    if (it.exception == null) {
//                        println("Collecting : ${it.text}")
//                    } else {
//                        //TODO
//                    }
//                }
                //TODO мок
                setState { MainViewState.Loading }
                delay(3000L)
                setState { MainViewState.Success(400) }
                delay(3000L)
                setState { MainViewState.Success(395) }
                delay(3000L)
                setState { MainViewState.Success(1000) }
                delay(3000L)
                setState { MainViewState.Success(5000) }
                delay(3000L)
                setState { MainViewState.Success(8192) }
                delay(3000L)
                setState { MainViewState.Success(532) }
            } catch (ex: Exception) {
                //TODO
            }
        }
    }

    override fun onCleared() {
        interactor.stopSocket()
        super.onCleared()
    }
}