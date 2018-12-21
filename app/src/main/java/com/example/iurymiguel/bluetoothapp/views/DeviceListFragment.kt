package com.example.iurymiguel.bluetoothapp.views

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.iurymiguel.bluetoothapp.R
import com.example.iurymiguel.bluetoothapp.databinding.FragmentDeviceListBinding
import com.example.iurymiguel.bluetoothapp.viewmodels.DeviceListViewModel
import com.example.iurymiguel.bluetoothapp.views.adapters.DeviceListRecyclerAdapter
import org.koin.android.ext.android.inject
import java.lang.Exception

class DeviceListFragment : Fragment() {

    private var mListener: BluetoothScannerAction? = null
    private lateinit var mViewModel: DeviceListViewModel
    private lateinit var mBinding: FragmentDeviceListBinding
    private val mAdapter: DeviceListRecyclerAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = activity?.run {
            ViewModelProviders.of(this).get(DeviceListViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        mViewModel.getDevicesLiveData().observe(this, Observer {
            mBinding.devicesList = it
            mAdapter.setDataSet(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_device_list, container, false)

        mBinding.devicesList = mViewModel.getDevicesLiveData().value
        mBinding.fragment = this

        mAdapter.setDataSet(mViewModel.getDevicesLiveData().value)

        mAdapter.setOnClickListener {
            mViewModel.mSelectedDevice = it
            view!!.findNavController().navigate(R.id.action_deviceListFragment_to_deviceInfoFragment)
        }

        val recyclerView: RecyclerView = mBinding.recyclerView

        recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context)
        }

        return mBinding.root
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