package com.example.iurymiguel.bluetoothapp.providers

import android.content.Context
import com.example.iurymiguel.bluetoothapp.bluetooth.BluetoothManagement

class BluetoothManagementProvider(private val context: Context) {
    fun getInstance() = BluetoothManagement(context)
}
