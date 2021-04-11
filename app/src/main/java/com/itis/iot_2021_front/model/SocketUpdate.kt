package com.itis.iot_2021_front.model

data class SocketUpdate(
    val eco2: Int = -1,
    val tvoc: Int = -1,
    val exception: Throwable? = null
)