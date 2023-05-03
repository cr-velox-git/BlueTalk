package com.velox.bluetalk.presenter

import com.velox.bluetalk.domain.chat.BluetoothDeviceDomain

data class BluetoothUiState(
    var scannedDevice: List<BluetoothDeviceDomain> = emptyList(),
    var pairedDevice: List<BluetoothDeviceDomain> = emptyList()
)
