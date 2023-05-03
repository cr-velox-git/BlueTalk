package com.velox.bluetalk.domain.chat


/**
 * typealias is use refer the object of one name to another name
 *
 * in this case [BluetoothDevice] can also refer as [BluetoothDeviceDomain]
 * */
typealias  BluetoothDeviceDomain = BluetoothDevice

data class BluetoothDevice(
    val name:String?,
    val address:String
)