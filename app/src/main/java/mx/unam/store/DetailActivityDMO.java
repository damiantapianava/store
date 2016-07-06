package mx.unam.store;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public abstract class DetailActivityDMO extends AppCompatActivity implements View.OnClickListener
{
    protected static final int REQUEST_CODE_EDIT_ACTIVITY = 2;

    protected TextView name_app;
    protected TextView name_dev;
    protected TextView details;
    protected TextView unistalled;
    protected CheckBox app_status;
    protected ActionBar action_bar;
    protected Button btn_uninstall;
    protected Button btn_open;
    protected Button btn_update;

    protected AppInfoModel info;

    protected Intent intent;

    protected AlertDialog.Builder builder;

    protected final String url = "https://www.kubofinanciero.com";

    protected boolean menu_edit_ENABLED;

    protected BroadcastReceiver receiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            int uninstall_status     = intent.getExtras().getInt("uninstall_status");
            int notification_type_id = intent.getExtras().getInt("notification_type_id");

            Log.d("receiver", "uninstall_status = " + uninstall_status);

            switch (uninstall_status)
            {
                case ServiceNotification.STARTED:
                    switch(notification_type_id)
                    {
                        case NotificationTask.UPDATE:
                            btn_uninstall.setEnabled(false);
                                 btn_open.setEnabled(false);
                            break;

                        case NotificationTask.UNINSTALL:
                            btn_uninstall.setVisibility(View.GONE);
                                 btn_open.setVisibility(View.GONE);
                               btn_update.setVisibility(View.GONE);
                            break;
                    }
                    break;

                case ServiceNotification.COMPLETED:
                    switch(notification_type_id)
                    {
                        case NotificationTask.UPDATE:
                            btn_uninstall.setEnabled(true);
                                 btn_open.setEnabled(true);
                               btn_update.setEnabled(false);
                            btn_update.setText(getResources().getText(R.string.notif_content_info_update));
                            app_status.setChecked(true);
                            break;

                        case NotificationTask.UNINSTALL:
                            menu_edit_ENABLED = false;
                            unistalled.setVisibility(View.VISIBLE);
                            break;
                    }

                    break;
            }
        }
    };

    protected void init_view()
    {
             name_app = (TextView) findViewById(R.id.tv_name_app);
             name_dev = (TextView) findViewById(R.id.tv_name_developer);
              details = (TextView) findViewById(R.id.tv_app_details);
           unistalled = (TextView) findViewById(R.id.app_status_unistalled);
           app_status = (CheckBox) findViewById(R.id.adapter_app_status);
        btn_uninstall = (Button) findViewById(R.id.btn_uninstall);
             btn_open = (Button) findViewById(R.id.btn_open);
           btn_update = (Button) findViewById(R.id.btn_update);

        btn_uninstall.setOnClickListener(this);
             btn_open.setOnClickListener(this);
           btn_update.setOnClickListener(this);
    }

    protected void init_toolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        action_bar = getSupportActionBar();

        action_bar.setTitle(R.string.str_toolbar_title);
        action_bar.setDisplayHomeAsUpEnabled(true);
        action_bar.setHomeButtonEnabled(true);
    }

    protected void init_intent_info(Intent intent)
    {
        info = (AppInfoModel) intent.getExtras().getSerializable("item");

        name_app.setText(info.getName_app());
        name_dev.setText(info.getName_dev());
        details.setText(info.getDetails());
        app_status.setChecked(info.getApp_status() > 0 ? true : false);

        if(info.getApp_status() > 0)
        {
            btn_update.setEnabled(false);
            btn_update.setText(getResources().getText(R.string.notif_content_info_update));

        } else {

            btn_update.setEnabled(true);
            btn_update.setText(getResources().getText(R.string.str_update_button));
        }
    }

    protected void init_uninstall()
    {
        builder = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.myDialog));
        builder.setMessage(R.string.dialog_confirm_delete);

        builder.setPositiveButton(R.string.btn_confirm_delete, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                intent = new Intent(getApplicationContext(), ServiceNotification.class);
                intent.putExtra("item", info);
                intent.putExtra("notification_type_id", NotificationTask.UNINSTALL);

                startService(intent);
            }
        });

        builder.setNegativeButton(R.string.btn_cancel_delete, new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                dialog.cancel();
            }
        });

        builder.create();
        builder.show();
    }

    protected void init_update()
    {
        intent = new Intent(getApplicationContext(), ServiceNotification.class);
        intent.putExtra("item", info);
        intent.putExtra("notification_type_id", NotificationTask.UPDATE);

        startService(intent);
    }
}
