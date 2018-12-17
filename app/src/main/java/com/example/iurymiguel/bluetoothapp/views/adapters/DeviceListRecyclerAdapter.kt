package com.example.iurymiguel.bluetoothapp.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.iurymiguel.bluetoothapp.databinding.DeviceViewHolderBinding
import com.example.iurymiguel.bluetoothapp.model.Device

class DeviceListRecyclerAdapter(private val mDevicesList: MutableList<Device>) :
    RecyclerView.Adapter<DeviceListRecyclerAdapter.DeviceViewHolder>() {

    class DeviceViewHolder(val binding: DeviceViewHolderBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(device: Device) {
            binding
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DeviceViewHolderBinding.inflate(layoutInflater, parent, false)

        return DeviceViewHolder(binding)
    }

    override fun getItemCount() = mDevicesList.count()

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}