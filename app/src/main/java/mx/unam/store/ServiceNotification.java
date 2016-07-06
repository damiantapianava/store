package mx.unam.store;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class ServiceNotification extends Service
{
    private AppInfoModel info;

    private NotificationTask task;
    private NotificationManager manager;

    private Bitmap large_icon;

    public final static int   STARTED = 1;
    public final static int COMPLETED = 2;

    public static final String ACTION_UNINSTALL ="mx.unam.store.ACTION_UNINSTALL";

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
        info = (AppInfoModel) intent.getExtras().getSerializable("item");

        large_icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_action_perm_identity);

        manager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        task = new NotificationTask();
        task.setContext(getApplicationContext());
        task.setResources(getResources());
        task.setManager(manager);
        task.setAppInfoModel(info);
        task.setLarge_icon(large_icon);
        task.execute();

/*
        sendBroadcast(new Intent().putExtra("uninstall_status", STARTED));
        sendBroadcast(new Intent().putExtra("uninstall_status", COMPLETED));
*/

        task = null;
        stopSelf();

        return START_STICKY;
    }
}
