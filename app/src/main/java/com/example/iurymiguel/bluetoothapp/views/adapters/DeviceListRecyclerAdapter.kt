package com.example.iurymiguel.bluetoothapp.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.iurymiguel.bluetoothapp.databinding.DeviceViewHolderBinding
import com.example.iurymiguel.bluetoothapp.model.Device

class DeviceListRecyclerAdapter(private val mDevicesList: MutableList<Device>?) :
    RecyclerView.Adapter<DeviceListRecyclerAdapter.DeviceViewHolder>() {

    class DeviceViewHolder(val binding: DeviceViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(device: Device) {
            binding.device = device
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DeviceViewHolderBinding.inflate(layoutInflater, parent, false)
        return DeviceViewHolder(binding)
    }

    override fun getItemCount() = mDevicesList?.let { mDevicesList.count() } ?: 0

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = mDevicesList!![position]
        holder.bind(device)
    }
}