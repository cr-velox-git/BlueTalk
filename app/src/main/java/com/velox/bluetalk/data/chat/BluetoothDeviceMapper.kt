package com.velox.bluetalk.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.velox.bluetalk.domain.chat.BluetoothDeviceDomain

@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain{
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}