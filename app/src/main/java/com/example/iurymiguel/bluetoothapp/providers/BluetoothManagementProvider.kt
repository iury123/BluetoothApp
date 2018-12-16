package com.example.iurymiguel.bluetoothapp.providers

import android.content.Context
import com.example.iurymiguel.bluetoothapp.bluetooth.BluetoothManagement

interface BluetoothManagementRepository {
    fun getInstance(): BluetoothManagement
}

class BluetoothManagementRepositoryImpl(private val context: Context) : BluetoothManagementRepository {
    override fun getInstance() = BluetoothManagement(context)
}


class BluetoothManagementProvider(private val bluetoothManagementRepository: BluetoothManagementRepository) {
    fun getInstance() = bluetoothManagementRepository.getInstance()
}

