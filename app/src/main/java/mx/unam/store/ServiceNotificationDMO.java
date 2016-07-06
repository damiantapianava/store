package mx.unam.store;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public abstract class ServiceNotificationDMO extends Service
{
    protected AppInfoModel info;

    protected NotificationTask task;
    protected NotificationManager manager;

    protected Bitmap large_icon;

    protected Intent intent;

    protected int notification_type_id;

    public final static int   STARTED = 1;
    public final static int COMPLETED = 2;

    public static final String ACTION_UNINSTALL ="mx.unam.store.ACTION_UNINSTALL";

    protected void init_task()
    {
        large_icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_stat_action_perm_identity);

        manager  = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        task = new NotificationTask(new AsyncResponse() {
            @Override
            public void processFinish()
            {
                stopSelf();
            }
        });

        task.setContext(getApplicationContext());
        task.setResources(getResources());
        task.setManager(manager);
        task.setAppInfoModel(info);
        task.setLarge_icon(large_icon);
        task.setNotification_type_id(notification_type_id);
        task.execute();

        task = null;

    }
}
