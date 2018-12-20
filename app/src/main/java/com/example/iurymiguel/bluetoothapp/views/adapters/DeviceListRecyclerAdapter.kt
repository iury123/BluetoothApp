package com.example.iurymiguel.bluetoothapp.views.adapters

import android.arch.lifecycle.MutableLiveData
import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.example.iurymiguel.bluetoothapp.databinding.DeviceViewHolderBinding
import com.example.iurymiguel.bluetoothapp.model.Device

class DeviceListRecyclerAdapter : RecyclerView.Adapter<DeviceListRecyclerAdapter.DeviceViewHolder>() {

    private var mDevicesList: MutableList<Device> = mutableListOf()
    private var mOnClickListenerCallback: ((Device) -> Unit)? = null

    class DeviceViewHolder(val binding: DeviceViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(device: Device) {
            binding.device = device
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DeviceViewHolderBinding.inflate(layoutInflater, parent, false)
        val holder = DeviceViewHolder(binding)
        binding.cardview.setOnClickListener { mOnClickListenerCallback?.let { it(mDevicesList[holder.adapterPosition]) }}
        return holder
    }

    override fun getItemCount() = mDevicesList.count()

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = mDevicesList[position]
        holder.bind(device)
    }

    fun setDataSet(list: MutableList<Device>?) {
        mDevicesList = list ?: mutableListOf()
        notifyDataSetChanged()
    }

    fun setOnClickListener(callback: (Device) -> Unit) {
        mOnClickListenerCallback = callback
    }
}

