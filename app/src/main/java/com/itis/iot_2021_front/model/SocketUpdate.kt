package com.itis.iot_2021_front.model

import okio.ByteString

data class SocketUpdate(
    val text: String? = null,
    val byteString: ByteString? = null,
    val exception: Throwable? = null
)