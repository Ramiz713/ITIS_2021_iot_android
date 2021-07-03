package com.itis.iot_2021_front.data.repository

import com.itis.iot_2021_front.data.api.IAirDetectorApi
import com.itis.iot_2021_front.model.AirDetectorData
import kotlinx.coroutines.delay

class MainRepository constructor(private val api: IAirDetectorApi) :
    IMainRepository {

    override suspend fun getAirSensorData(): AirDetectorData {
        return api.getAirSensorData()
    }

    override suspend fun getMockAirSensorData(): AirDetectorData {
        delay(1500L)
        return airDetectorDataList.random()
    }

    private val airDetectorDataList = listOf(
        AirDetectorData(400, 102),
        AirDetectorData(600, 100),
        AirDetectorData(700, 101),
        AirDetectorData(1000, 99),
        AirDetectorData(1500, 103),
        AirDetectorData(2000, 98),
        AirDetectorData(720, 98)
    )
}