package com.example.iurymiguel.bluetoothapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.iurymiguel.bluetoothapp.model.Device

class DeviceListViewModel: ViewModel() {

    private lateinit var mDevices: MutableLiveData<MutableList<Device>>
    lateinit var mSelectedDevice: Device

    fun getDevicesLiveData() : MutableLiveData<MutableList<Device>> {
        if(!::mDevices.isInitialized) {
            mDevices = MutableLiveData()
        }
        return mDevices
    }
}