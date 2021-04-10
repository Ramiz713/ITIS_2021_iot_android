package com.itis.iot_2021_front.data.repository

import com.itis.iot_2021_front.model.SocketUpdate
import kotlinx.coroutines.channels.Channel

interface IMainInteractor {
    fun startSocket(): Channel<SocketUpdate>
    fun stopSocket()
}