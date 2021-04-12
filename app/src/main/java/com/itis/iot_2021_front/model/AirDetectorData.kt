package com.itis.iot_2021_front.model

import com.google.gson.annotations.SerializedName

data class AirDetectorData(
    @SerializedName("eco2")
    val eco2: Int,
    @SerializedName("tvoc")
    val tvoc: Int
)