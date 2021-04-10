package com.itis.iot_2021_front.data.interactor

import com.itis.iot_2021_front.data.websocket.IWebServicesProvider
import com.itis.iot_2021_front.data.websocket.WebServicesProvider
import com.itis.iot_2021_front.model.SocketUpdate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel

class MainRepository constructor(private val webServicesProvider: IWebServicesProvider) :
    IMainRepository {

    override fun startSocket(): Channel<SocketUpdate> =
        webServicesProvider.startSocket()

    override fun closeSocket() {
        webServicesProvider.stopSocket()
    }
}