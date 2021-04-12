package com.itis.iot_2021_front.di

import com.google.gson.GsonBuilder
import com.itis.iot_2021_front.data.api.IAirDetectorApi
import com.itis.iot_2021_front.data.repository.IMainRepository
import com.itis.iot_2021_front.data.repository.MainRepository
import com.itis.iot_2021_front.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single<IAirDetectorApi> {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://iot-detector.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
        retrofit.create(IAirDetectorApi::class.java)
    }
    single<IMainRepository> { MainRepository(get()) }

    viewModel { MainViewModel(get()) }
}
