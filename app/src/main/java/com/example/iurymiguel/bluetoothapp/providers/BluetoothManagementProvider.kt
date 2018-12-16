package com.example.iurymiguel.bluetoothapp.providers

import android.content.Context
import com.example.iurymiguel.bluetoothapp.bluetooth.BluetoothManagement

interface BluetoothManagementRepository {
    fun getInstance(): BluetoothManagement
}


class BluetoothManagementRepositoryImpl(val context: Context) : BluetoothManagementRepository {
    override fun getInstance() = BluetoothManagement(context)
}


class BluetoothManagementProvider(val bluetoothManagementRepository: BluetoothManagementRepository) {
    fun getInstance() = bluetoothManagementRepository.getInstance()
}

