package com.itis.iot_2021_front.data.repository

import com.itis.iot_2021_front.model.AirDetectorData

interface IMainRepository {
    suspend fun getAirSensorData(): AirDetectorData
    suspend fun getMockAirSensorData(): AirDetectorData
}