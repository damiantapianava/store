package mx.unam.store;

import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ServiceNotification extends ServiceNotificationDMO
{
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        info = (AppInfoModel)  intent.getExtras().getSerializable("item");
        notification_type_id = intent.getExtras().getInt("notification_type_id");

        init_task();

        return START_STICKY;
    }
}
