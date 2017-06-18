package lab373.android.bluetoothlegatt.systemoverlaywindow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.view.View;
import android.view.WindowManager;

public class FloatingWindow extends Service {
    private Context mContext;
    private WindowManager mWindowManager;
    private View mView;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
    }

    public FloatingWindow() {
    }

}
