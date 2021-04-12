package com.itis.iot_2021_front.data.api

import com.itis.iot_2021_front.model.AirDetectorData
import retrofit2.http.GET

interface IAirDetectorApi {

    @GET("/carbon-dioxide")
    suspend fun getAirSensorData(): AirDetectorData
}