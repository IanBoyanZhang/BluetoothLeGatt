package lab373.android.bluetoothlegatt.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

//import lab373.android.bluetoothlegatt.R;

public class ModeOptions extends Service {
    private Context mContext;
    private WindowManager mWindowManager;
    private View mView;

    public ModeOptions() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    public class LocalBinder extends Binder {
        public ModeOptions getService() { return ModeOptions.this; }
    }

    private final IBinder mBinder = new LocalBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    }
}
