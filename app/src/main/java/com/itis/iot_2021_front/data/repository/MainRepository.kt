package com.itis.iot_2021_front.data.repository

import com.itis.iot_2021_front.data.api.IAirDetectorApi
import com.itis.iot_2021_front.model.AirDetectorData

class MainRepository constructor(private val api: IAirDetectorApi) :
    IMainRepository {

    override suspend fun getAirSensorData(): AirDetectorData {
        return api.getAirSensorData()
    }
}