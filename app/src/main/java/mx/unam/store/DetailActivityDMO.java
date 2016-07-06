package mx.unam.store;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ContextThemeWrapper;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

    protected AppInfoModel info;

    protected Intent intent;

    protected AlertDialog.Builder builder;

    protected final String url = "https://www.kubofinanciero.com";

    protected boolean menu_edit_ENABLED;

    protected void init_view()
    {
          name_app = (TextView) findViewById(R.id.tv_name_app);
          name_dev = (TextView) findViewById(R.id.tv_name_developer);
           details = (TextView) findViewById(R.id.tv_app_details);
        unistalled = (TextView) findViewById(R.id.app_status_unistalled);
        app_status = (CheckBox) findViewById(R.id.adapter_app_status);

        findViewById(R.id.btn_uninstall).setOnClickListener(this);
        findViewById(R.id.btn_open).setOnClickListener(this);
        findViewById(R.id.btn_update).setOnClickListener(this);
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
