package com.example.iurymiguel.bluetoothapp.model

import android.databinding.BindingAdapter
import android.widget.TextView

data class Device(var name: String? = "", var macAddress: String? = "", var rssi: Int? = 0) {
    companion object {
        @BindingAdapter("android:text")
        fun setText(view: TextView, value: Long) {
            view.text = value.toString()
        }
    }
}