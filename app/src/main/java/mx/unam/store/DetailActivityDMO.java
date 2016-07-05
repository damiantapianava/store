package mx.unam.store;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
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
    protected CheckBox app_status;
    protected ActionBar action_bar;
    protected AppInfoModel info;

    protected Intent intent;

    protected final String url = "https://www.kubofinanciero.com";

    protected void init_view()
    {
        name_app = (TextView) findViewById(R.id.tv_name_app);
        name_dev = (TextView) findViewById(R.id.tv_name_developer);
        details = (TextView) findViewById(R.id.tv_app_details);
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
}
