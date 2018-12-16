package com.example.iurymiguel.bluetoothapp.views

import android.Manifest
import android.arch.lifecycle.ViewModelProviders
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Toast
import com.example.iurymiguel.bluetoothapp.R
import com.example.iurymiguel.bluetoothapp.providers.BluetoothManagementProvider
import com.example.iurymiguel.bluetoothapp.viewmodels.DeviceListViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), BluetoothScannerAction {

    private var mLocationPermission: Int = 0
    private val MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1
    private val TIME_TO_DISMISS_TOAST: Long = 4000
    private lateinit var mViewModel: DeviceListViewModel
    private val mBluetoothProvider: BluetoothManagementProvider by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel = ViewModelProviders.of(this).get(DeviceListViewModel::class.java)

        askForLocationPermission()
    }


    override fun onResume() {
        super.onResume()
        if (mLocationPermission == PackageManager.PERMISSION_GRANTED) {
            askForEnablingLocation()
        }
        enableBluetooth()
    }


    private fun askForLocationPermission() {
        mLocationPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
        if (mLocationPermission != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            ) {
                showWarning()
            } else {
                requestPermission()
            }
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ_CONTACTS -> {
                if (grantResults.isNotEmpty() &&
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    askForEnablingLocation()
                }
            }
        }
    }


    private fun requestPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
            MY_PERMISSIONS_REQUEST_READ_CONTACTS
        )
    }


    private fun showWarning() {
        Toast.makeText(this, getString(R.string.enable_location_warning), Toast.LENGTH_LONG).show()
        Handler().postDelayed(object : Runnable {
            override fun run() {
                requestPermission()
            }
        }, TIME_TO_DISMISS_TOAST)
    }

    private fun askForEnablingLocation() {

    }


    /**
     * Enables bluetooth if disabled.
     */
    private fun enableBluetooth() {
        val bluetoothAdapter = BluetoothAdapter.getDefaultAdapter()
        if (!bluetoothAdapter.isEnabled) {
            bluetoothAdapter.enable()
        }
    }

    override fun onActionEmmited(startScanner: Boolean) {
        val toastMessage = when (startScanner) {
            true -> {
                if (!mBluetoothProvider.getInstance().mScanning) {
                    mBluetoothProvider.getInstance().scanDevice(true)
                    "Scanner ativado."
                } else {
                    "Scanner já ativado."
                }
            }
            else -> {
                if (mBluetoothProvider.getInstance().mScanning) {
                    mBluetoothProvider.getInstance().scanDevice(false)
                    "Scanner desativado."
                } else {
                    "Scanner já desativado."
                }
            }
        }
        Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
    }
}
