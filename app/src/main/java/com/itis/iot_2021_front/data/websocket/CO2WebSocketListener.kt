package com.itis.iot_2021_front.data.websocket

import android.util.Log
import com.itis.iot_2021_front.data.websocket.WebServicesProvider.Companion.NORMAL_CLOSURE_STATUS
import com.itis.iot_2021_front.model.SocketAbortedException
import com.itis.iot_2021_front.model.SocketUpdate
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import org.json.JSONObject

class CO2WebSocketListener : WebSocketListener() {

    val socketEventChannel: Channel<SocketUpdate> = Channel(10)

    override fun onOpen(webSocket: WebSocket, response: Response) {
    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        GlobalScope.launch {
            val json = JSONObject(text)
            val socketUpdate = SocketUpdate(json.optInt("eco2"), json.optInt("tvoc"))
            socketEventChannel.send(socketUpdate)
        }
    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        GlobalScope.launch {
            socketEventChannel.send(SocketUpdate(exception = SocketAbortedException()))
        }
        webSocket.close(NORMAL_CLOSURE_STATUS, null)
        socketEventChannel.close()
    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        GlobalScope.launch {
            socketEventChannel.send(SocketUpdate(exception = t))
        }
    }
}