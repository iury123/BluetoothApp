package com.example.iurymiguel.bluetoothapp.application

import android.app.Application
import com.example.iurymiguel.bluetoothapp.providers.BluetoothManagementProvider
import com.example.iurymiguel.bluetoothapp.providers.BluetoothManagementRepository
import com.example.iurymiguel.bluetoothapp.providers.BluetoothManagementRepositoryImpl
import org.koin.android.ext.android.startKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

class MyApplication: Application() {

    private val appModule = module {
        single<BluetoothManagementRepository> { BluetoothManagementRepositoryImpl(androidContext()) }
        factory { BluetoothManagementProvider(get()) }
    }

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}