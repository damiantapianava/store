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

        intent = new Intent(ACTION_UNINSTALL);
        intent.putExtra("uninstall_status", STARTED);
        intent.putExtra("notification_type_id", notification_type_id);

        sendBroadcast(intent);

        init_task();

        return START_STICKY;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

        intent = new Intent(ACTION_UNINSTALL);
        intent.putExtra("uninstall_status", COMPLETED);
        intent.putExtra("notification_type_id", notification_type_id);

        sendBroadcast(intent);
    }
}
