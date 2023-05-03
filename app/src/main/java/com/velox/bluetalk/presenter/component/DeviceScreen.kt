package com.velox.bluetalk.presenter.component

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.velox.bluetalk.domain.chat.BluetoothDevice
import com.velox.bluetalk.presenter.BluetoothUiState

@Composable
fun DeviceScreen(
    state: BluetoothUiState,
    onStartScan: () -> Unit,
    onStopScan: () -> Unit,
) {
    Column(Modifier.fillMaxSize()) {

        BluetoothDeviceList(pairedDevices = state.pairedDevice, scannedDevices = state.scannedDevice,
            onClick ={},
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            )

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = onStartScan) {
                Text(text = "Start Scan")
            }
            Button(onClick = onStopScan) {
                Text(text = "Stop Scan")
            }
        }
    }
}

@Composable
fun BluetoothDeviceList(
    pairedDevices: List<BluetoothDevice>,
    scannedDevices: List<BluetoothDevice>,
    onClick: (BluetoothDevice) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        item {
            Text(
                text = "Paired Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        items(pairedDevices){device->
            Text(text = device.name ?: "(No Name)",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(device) }
                .padding(16.dp)
                )

        }

        item {
            Text(
                text = "Scanned Devices",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        items(scannedDevices){device->
            Toast.makeText(LocalContext.current, "${device.name}", Toast.LENGTH_SHORT).show()
            Text(text = device.name ?: "(No Name)",
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick(device) }
                .padding(16.dp)
                )

        }
    }
}