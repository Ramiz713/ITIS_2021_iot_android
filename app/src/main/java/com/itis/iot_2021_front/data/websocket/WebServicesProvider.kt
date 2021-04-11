package com.itis.iot_2021_front.data.websocket

import com.itis.iot_2021_front.model.SocketUpdate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import java.util.concurrent.TimeUnit

class WebServicesProvider(private val webSocketUrl: String) : IWebServicesProvider {

    companion object {
        const val NORMAL_CLOSURE_STATUS = 1000
    }

    private var _webSocket: WebSocket? = null

    private val socketOkHttpClient = OkHttpClient.Builder()
        .readTimeout(30, TimeUnit.SECONDS)
        .connectTimeout(39, TimeUnit.SECONDS)
        .hostnameVerifier { _, _ -> true }
        .build()

    private var _webSocketListener: CO2WebSocketListener? = null

    override fun startSocket(): Channel<SocketUpdate> =
        with(CO2WebSocketListener()) {
            startSocket(this)
            this@with.socketEventChannel
        }

    override fun stopSocket() {
        try {
            _webSocket?.close(NORMAL_CLOSURE_STATUS, null)
            _webSocket = null
            _webSocketListener?.socketEventChannel?.close()
            _webSocketListener = null
        } catch (ex: Exception) {
        }
    }

    private fun startSocket(webSocketListener: CO2WebSocketListener) {
        _webSocketListener = webSocketListener
        _webSocket = socketOkHttpClient.newWebSocket(
            Request.Builder().url(webSocketUrl).build(),
            webSocketListener
        )
        socketOkHttpClient.connectionPool.evictAll()
    }
}