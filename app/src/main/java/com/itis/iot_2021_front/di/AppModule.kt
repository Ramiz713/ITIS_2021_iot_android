package com.itis.iot_2021_front.di

import com.itis.iot_2021_front.data.interactor.IMainRepository
import com.itis.iot_2021_front.data.interactor.MainRepository
import com.itis.iot_2021_front.data.repository.IMainInteractor
import com.itis.iot_2021_front.data.repository.MainInteractor
import com.itis.iot_2021_front.data.websocket.IWebServicesProvider
import com.itis.iot_2021_front.data.websocket.WebServicesProvider
import com.itis.iot_2021_front.ui.main.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val appModule = module {

    single<IWebServicesProvider> { WebServicesProvider("wss://iot-detector.herokuapp.com/carbon-dioxide") }
    single<IMainRepository> { MainRepository(get()) }
    single<IMainInteractor> { MainInteractor(get()) }

    viewModel { MainViewModel(get()) }
}
