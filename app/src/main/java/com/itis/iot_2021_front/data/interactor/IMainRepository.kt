package com.itis.iot_2021_front.data.interactor

import com.itis.iot_2021_front.model.SocketUpdate
import kotlinx.coroutines.channels.Channel

interface IMainRepository {
    fun startSocket(): Channel<SocketUpdate>
    fun closeSocket()
}