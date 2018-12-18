package com.example.iurymiguel.bluetoothapp.application

import android.app.Application
import com.example.iurymiguel.bluetoothapp.bluetooth.BluetoothManagement
import com.example.iurymiguel.bluetoothapp.views.adapters.DeviceListRecyclerAdapter
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

class MyApplication: Application() {

    private val appModule = module {
        single { BluetoothManagement(androidContext()) }
        factory { DeviceListRecyclerAdapter() }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}