package lab373.android.bluetoothlegatt.adapters;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lab373.android.bluetoothlegatt.R;
import lab373.android.bluetoothlegatt.callbacks.OnDeviceSelectedListener;

/**
 * Created by weisi on 4/10/17.
 */

public class LeDeviceListAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<BluetoothDevice> mLeDevices;
    OnDeviceSelectedListener listener;

    public LeDeviceListAdapter(Context mContext, ArrayList<BluetoothDevice> mLeDevices, OnDeviceSelectedListener listener) {
        this.mContext = mContext;
        this.mLeDevices = mLeDevices;
        this.listener = listener;

    }

    public void addDevice(BluetoothDevice device) {
        if(!mLeDevices.contains(device)) {
            mLeDevices.add(device);
        }
    }

    public BluetoothDevice getDevice(int position) {
        return mLeDevices.get(position);
    }

    public void clear() {
        mLeDevices.clear();
    }

    @Override
    public int getCount() {
        return mLeDevices.size();
    }

    @Override
    public Object getItem(int position) {
        return mLeDevices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;
        // General ListView optimization code.
        if (view == null) {
            LayoutInflater mInflator = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = mInflator.inflate(R.layout.listitem_device, null);
            viewHolder = new ViewHolder();
            viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
            viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        final BluetoothDevice device = mLeDevices.get(position);
        final String deviceName = device.getName();
        if (deviceName != null && deviceName.length() > 0)
            viewHolder.deviceName.setText(deviceName);
        else
            viewHolder.deviceName.setText(R.string.unknown_device);
        viewHolder.deviceAddress.setText(device.getAddress());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.SelectedDevice(device);
            }
        });


        return view;
    }

    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }

}
