package com.itis.iot_2021_front.data.repository

import com.itis.iot_2021_front.data.interactor.IMainRepository
import com.itis.iot_2021_front.model.SocketUpdate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel

class MainInteractor constructor(private val repository: IMainRepository): IMainInteractor {

    override fun startSocket(): Channel<SocketUpdate> = repository.startSocket()

    override fun stopSocket() {
        repository.closeSocket()
    }
}