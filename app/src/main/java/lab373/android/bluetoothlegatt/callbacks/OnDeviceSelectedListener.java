package lab373.android.bluetoothlegatt.callbacks;

import android.bluetooth.BluetoothDevice;

/**
 * Created by ianzhang on 4/10/17.
 */

public interface OnDeviceSelectedListener {

    void SelectedDevice(BluetoothDevice device);
}