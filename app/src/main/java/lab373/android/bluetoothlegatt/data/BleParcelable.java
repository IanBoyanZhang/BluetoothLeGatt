package lab373.android.bluetoothlegatt.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ianzhang on 7/22/17.
 */

public class BleParcelable implements Parcelable {
    private int mData;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(mData);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<BleParcelable> CREATOR = new Parcelable.Creator<BleParcelable>() {
        public BleParcelable createFromParcel(Parcel in) {
            return new BleParcelable(in);
        }

        public BleParcelable[] newArray(int size) {
            return new BleParcelable[size];
        }
    };

    private BleParcelable(Parcel in) {
        mData = in.readInt();
    }
}
