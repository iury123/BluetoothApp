package com.example.iurymiguel.bluetoothapp.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iurymiguel.bluetoothapp.R
import com.example.iurymiguel.bluetoothapp.databinding.FragmentDeviceListBinding
import com.example.iurymiguel.bluetoothapp.viewmodels.DeviceListViewModel
import java.lang.Exception

class DeviceListFragment : Fragment() {

    private var mListener: BluetoothScannerAction? = null
    private lateinit var mViewModel: DeviceListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = activity?.run {
            ViewModelProviders.of(this).get(DeviceListViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        mViewModel.getDevices().observe(this, Observer {

        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDeviceListBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_device_list, container, false)

        binding.devicesList = mViewModel.getDevices().value
        binding.fragment = this

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BluetoothScannerAction) {
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    /**
     * Starts Scan as the button is clicked.
     */
    fun startScan() {
        mListener?.onActionEmmited(true)
    }

    /**
     * Stops Scan as the button is clicked.
     */
    fun stopScan() {
        mListener?.onActionEmmited(false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DeviceListFragment()
    }
}

interface BluetoothScannerAction {
    fun onActionEmmited(startScanner: Boolean)
}