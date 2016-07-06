package mx.unam.store;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v4.app.NotificationCompat;

public abstract class NotificationTaskDMO extends AsyncTask<Integer, Integer, Boolean>
{
    protected Context context;

    protected Resources resources;

    protected NotificationManager manager;
    protected NotificationCompat.Builder mNotif;

    protected Intent intent;
    protected PendingIntent pendingIntent;
    protected PendingIntent piService;

    protected AppInfoDAO dao;
    protected AppInfoModel info;
    protected AsyncResponse delegate;

    protected Bitmap large_icon;

    protected int id;
    protected int counter_limit;
    protected int notification_type_id;

    public final static int    UPDATE = 1;
    public final static int UNINSTALL = 2;

    public void setAppInfoModel(AppInfoModel info)
    {
        this.info = info;
    }

    public void setContext(Context context)
    {
        this.context = context;
    }

    public void setManager(NotificationManager manager)
    {
        this.manager = manager;
    }

    public void setLarge_icon(Bitmap bitmap)
    {
        large_icon = bitmap;
    }

    public void setResources(Resources resources)
    {
        this.resources = resources;
    }

    public void setNotification_type_id(int notification_type_id) {
        this.notification_type_id = notification_type_id;
    }
}
