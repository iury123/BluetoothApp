package com.example.iurymiguel.bluetoothapp.utils

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.widget.Toast

class Utils {

    companion object {

        private var toast: Toast? = null

        fun showToast(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {
            if (toast != null) {
                toast!!.cancel()
            }
            toast = Toast.makeText(context, message, duration)
            toast!!.show()
        }
    }
}