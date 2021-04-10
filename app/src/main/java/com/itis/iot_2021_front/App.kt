package com.itis.iot_2021_front

import android.app.Application
import com.itis.iot_2021_front.di.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(
            this@App,
            listOf(appModule)
        )
    }
}