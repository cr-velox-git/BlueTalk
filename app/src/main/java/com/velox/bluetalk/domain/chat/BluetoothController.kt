package com.velox.bluetalk.domain.chat

import kotlinx.coroutines.flow.StateFlow

//all the relevant bluetooth functions are defined her
interface BluetoothController {
    val scannedDevices : StateFlow<List<BluetoothDevice>>
    val pairedDevices : StateFlow<List<BluetoothDevice>>

    fun startDiscovery()
    fun stopDiscovery()

    //free up all the memory and resources used by the bluetooth controller
    fun release()
}