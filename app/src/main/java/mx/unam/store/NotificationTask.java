package mx.unam.store;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class NotificationTask extends NotificationTaskDMO
{
    protected NotificationTask(AsyncResponse asyncResponse)
    {
        delegate = asyncResponse;
    }

    @Override
    protected void onPreExecute()
    {
        mNotif = new NotificationCompat.Builder(context);
        mNotif.setLargeIcon(large_icon);
        mNotif.setSmallIcon(android.R.drawable.ic_dialog_email);

        switch (notification_type_id)
        {
            case UPDATE:
                counter_limit = 10;
                mNotif.setContentTitle(resources.getString(R.string.notif_content_title_pre_exc_update));
                mNotif.setContentText( resources.getString(R.string.notif_content_text_pre_exc_update));
                break;

            case UNINSTALL:
                counter_limit = 5;
                mNotif.setContentTitle(resources.getString(R.string.notif_content_title_pre_exc));
                mNotif.setContentText (resources.getString(R.string.notif_content_text_pre_exc));
                break;
        }
    }

    @Override
    protected Boolean doInBackground(Integer... params)
    {
        for (int i = 0; i < counter_limit; i++)
        {
            publishProgress(i);

            try
            {
                Thread.sleep(1000 * 1);

            } catch (InterruptedException e) {

                e.printStackTrace();

                return false;
            }
        }

        dao = new AppInfoDAO(context);

        switch (notification_type_id)
        {
            case UPDATE:
                info.setApp_status(UPDATE);

                boolean update_OK = dao.update(info);

                return update_OK;

            case UNINSTALL:
                boolean delete_OK = dao.delete(info);

                return delete_OK;

            default: return true;
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values)
    {
        mNotif.setProgress(counter_limit, values[0], false);

        manager.notify(id, mNotif.build());
    }

    @Override
    protected void onPostExecute(Boolean result)
    {
        if(result)
        {
            mNotif.setAutoCancel(true);
            mNotif.setProgress(0, 0, false);

            switch (notification_type_id)
            {
                case UPDATE:
                    mNotif.setContentTitle(resources.getString(R.string.notif_content_title_update));
                    mNotif.setContentText (resources.getString(R.string.notif_content_text_update));
                    mNotif.setContentInfo (resources.getString(R.string.notif_content_info_update));
                    mNotif.setStyle(new NotificationCompat.BigTextStyle().bigText(String.format(resources.getString(R.string.notif_bigtext_style_update))));
                    break;

                case UNINSTALL:
                    mNotif.setContentTitle(resources.getString(R.string.notif_content_title));
                    mNotif.setContentText (resources.getString(R.string.notif_content_text));
                    mNotif.setContentInfo (resources.getString(R.string.notif_content_info));
                    mNotif.setStyle(new NotificationCompat.BigTextStyle().bigText(String.format(resources.getString(R.string.notif_bigtext_style))));
                    break;
            }

            intent = new Intent(context, DetailActivity.class);
            intent.putExtra("item", info);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            mNotif.setContentIntent(pendingIntent);

/*
            intent = new Intent(context, ServiceNotification.class);
            intent.putExtra("key_id", id + 1);

            piService = PendingIntent.getService(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            mNotif.addAction(android.R.drawable.ic_input_add, "Descargar de nuevo", piService);
*/

            manager.notify(id, mNotif.build());

            delegate.processFinish();
        }
    }
}
