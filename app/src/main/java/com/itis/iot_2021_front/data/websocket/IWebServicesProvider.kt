package com.itis.iot_2021_front.data.websocket

import com.itis.iot_2021_front.model.SocketUpdate
import kotlinx.coroutines.channels.Channel

interface IWebServicesProvider {
    fun startSocket(): Channel<SocketUpdate>
    fun stopSocket()
}