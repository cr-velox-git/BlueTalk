package com.velox.bluetalk.presenter

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.velox.bluetalk.presenter.component.DeviceScreen
import com.velox.bluetalk.ui.theme.BlueTalkTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val bluetoothManger by lazy { applicationContext.getSystemService(BluetoothManager::class.java) }

    private val bluetoothAdapter by lazy { bluetoothManger?.adapter }

    private val isBluetoothEnabled:Boolean
        get() = bluetoothAdapter?.isEnabled == true

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            /*not needed*/
        }
        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { perms ->

            if ( perms[Manifest.permission.BLUETOOTH_SCAN] == true &&
                perms[Manifest.permission.BLUETOOTH_CONNECT] == true){
                Toast.makeText(this, "all permission granted", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "provide necessary permissions", Toast.LENGTH_SHORT).show()
            }

            val canEnableBluetooth =if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){

                perms[Manifest.permission.BLUETOOTH_SCAN] == true &&
                perms[Manifest.permission.BLUETOOTH_CONNECT] == true

            }else true

            if(canEnableBluetooth && !isBluetoothEnabled){
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            permissionLauncher.launch(arrayOf(
                Manifest.permission.BLUETOOTH_SCAN,
                Manifest.permission.BLUETOOTH_CONNECT
            ))
        }

        setContent {
            BlueTalkTheme {
                val viewModel = hiltViewModel<BluetoothViewModel>()
                val state by viewModel.state.collectAsState()


                Surface(color = MaterialTheme.colorScheme.background) {
                    DeviceScreen(
                        state = state,
                        onStartScan = viewModel::startScan,
                        onStopScan = viewModel::stopScan
                    )
                }
            }
        }
    }

    private fun checkForPermissions(): Boolean {
        if (
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_CONNECT
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.BLUETOOTH_SCAN
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }


}
