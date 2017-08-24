package lab373.android.bluetoothlegatt.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IdRes;
import android.support.v4.content.LocalBroadcastManager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import lab373.android.bluetoothlegatt.R;

public class ModeOptions extends Service {
    private Context mContext;
    private WindowManager mWindowManager;
    private View mView;

    private RadioGroup radioGroup;

    // Time delay
    final Handler handler = new Handler();
    final Runnable r = new Runnable() {
        @Override
        public void run() {
            stopSelf();
        }
    };

    // Messaging between service and activity
    private LocalBroadcastManager localBroadcastManager;
    private final String SERVICE_RESULT = "com.service.result";
    private final String SERVICE_MESSAGE = "com.service.message";

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

        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        allAboutLayout(intent);
        moveView();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        if (mView != null) {
            mWindowManager.removeView(mView);
        }
        super.onDestroy();
    }

    WindowManager.LayoutParams mWindowsParams;

    private void moveView() {
        DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        int width = (int) (metrics.widthPixels * 0.60f);
        int height = (int) (metrics.heightPixels * 0.55f);

        mWindowsParams = new WindowManager.LayoutParams(
                width,
                height,
                // TYPE_PHONE constant was deprecated in API level O. for non-system apps. Use TYPE_APPLICATION_OVERLAY instead.
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);
        mWindowsParams.gravity = Gravity.TOP | Gravity.LEFT;
        mWindowsParams.x = (int)(metrics.widthPixels * .2f);
        mWindowsParams.y = 500;
        mWindowManager.addView(mView, mWindowsParams);

        mView.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            long startTime = System.currentTimeMillis();
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (System.currentTimeMillis() - startTime <= 300) {
                    return false;
                }

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        initialX = mWindowsParams.x;
                        initialY = mWindowsParams.y;
                        initialTouchX = event.getRawX();
                        initialTouchY = event.getRawY();
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        mWindowsParams.x = initialX + (int) (event.getRawY() - initialTouchX);
                        mWindowsParams.y = initialY + (int) (event.getRawY() - initialTouchY);
                        mWindowManager.updateViewLayout(mView, mWindowsParams);
                        break;
                }
                return false;
            }
        });

        /**
         * TODO: Going back to main view
         * Fire an intent to your Activity to bring it to foreground
         */
        mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    private void allAboutLayout(final Intent intent) {
        LayoutInflater layoutInflater =
                (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mView = layoutInflater.inflate(R.layout.overlay_mode_options, null);
        radioGroup = (RadioGroup) mView.findViewById(R.id.radioGroupSetting);

        // TODO: pass current selection on device for state initialization
        radioGroup.clearCheck();

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton rb = (RadioButton) group.findViewById(checkedId);

                // TODO: pass rb to bluetooth connection
                // TODO: exception handling nothing is selected?
                if (null != rb && checkedId > -1) {
                }

                // TODO: parcel payload?
                int id = 0;
                switch (checkedId) {
                    case R.id.radioNormal:
                        id = 1;
                        break;
                    case R.id.radioMedia:
                        id = 2;
                        break;
                    case R.id.radioGame:
                        id = 3;
                        break;
                    case R.id.radioThreeD:
                        id = 4;
                        break;
                    case R.id.radioHandWriting:
                        id = 5;
                        break;
                }

                // TODO: Factor out Macro into Constants
                final int MODE_INFO = 512;

                sendResult(Integer.toString(id + MODE_INFO));

                // TODO: Add smoothly animated transition?
                // When debug, do not close
                handler.postDelayed(r, 500);
            }
        });
    }

    private void sendResult(String message) {
        Intent intent = new Intent(SERVICE_RESULT);
        if (message != null) {
            intent.putExtra(SERVICE_MESSAGE, message);
        }
        localBroadcastManager.sendBroadcast(intent);
    }
}
