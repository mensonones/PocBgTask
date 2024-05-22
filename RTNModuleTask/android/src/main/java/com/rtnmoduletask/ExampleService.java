package com.rtnmoduletask;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ServiceInfo;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationManager;
import android.app.NotificationChannel;

import android.graphics.BitmapFactory;

import androidx.core.content.ContextCompat;

import com.facebook.react.HeadlessJsTaskService;

public class ExampleService extends Service {

    private static final int SERVICE_NOTIFICATION_ID = 100001;
    private static final String CHANNEL_ID = "EXAMPLE";
    private Handler handler = new Handler();
    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            Context context = getApplicationContext();
            Intent myIntent = new Intent(context, ExampleEventService.class);
            context.startService(myIntent);
            HeadlessJsTaskService.acquireWakeLockNow(context);
            handler.postDelayed(this, 300000); // 5 Min
        }
    };

    public ExampleService() {
        super();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            startForeground(SERVICE_NOTIFICATION_ID, getMyNotification(), ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC);
        } else {
            startForeground(SERVICE_NOTIFICATION_ID, getMyNotification());
        }
    }

    private Notification getMyNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_notification))
            .setContentTitle("Serviço em Background")
            .setContentText("O serviço está rodando em background.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOngoing(true);
        return builder.build();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnableCode);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(runnableCode);
        return START_STICKY_COMPATIBILITY;
    }
}