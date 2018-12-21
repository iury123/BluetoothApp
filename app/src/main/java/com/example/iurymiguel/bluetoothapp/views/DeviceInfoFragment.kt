package com.example.iurymiguel.bluetoothapp.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.iurymiguel.bluetoothapp.R
import com.example.iurymiguel.bluetoothapp.databinding.FragmentDeviceInfoBinding
import com.example.iurymiguel.bluetoothapp.viewmodels.DeviceListViewModel
import java.lang.Exception

class DeviceInfoFragment : Fragment() {

    private lateinit var mViewModel: DeviceListViewModel
    private lateinit var mBinding: FragmentDeviceInfoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = activity?.run {
            ViewModelProviders.of(this).get(DeviceListViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil
            .inflate(inflater, R.layout.fragment_device_info, container, false)

        mBinding.device = mViewModel.mSelectedDevice
        mBinding.fragment = this

        return mBinding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = DeviceInfoFragment()
    }
}
