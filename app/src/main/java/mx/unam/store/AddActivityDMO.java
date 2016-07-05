package mx.unam.store;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public abstract class AddActivityDMO extends AppCompatActivity
        implements View.OnClickListener, CompoundButton.OnCheckedChangeListener
{
    protected ActionBar action_bar;

    protected EditText name_app;
    protected EditText name_dev;
    protected EditText details;
    protected CheckBox checkBox;
    protected Button btn_save;
    protected Button btn_edit;

    protected AppInfoDAO dao;
    protected AppInfoModel info;

    protected Intent intent;

    protected boolean check_app_status;
    protected boolean edit_ENABLED;
    protected boolean persist_OK;

    protected void init_view()
    {
        name_app = (EditText) findViewById(R.id.et_name_app);
        name_dev = (EditText) findViewById(R.id.et_name_developer);
        details  = (EditText) findViewById(R.id.et_app_details);
        checkBox = (CheckBox) findViewById(R.id.check_app_status);

        btn_edit = (Button) findViewById(R.id.btn_edit);
        btn_save = (Button) findViewById(R.id.btnSave);

        btn_save.setOnClickListener(this);
        btn_edit.setOnClickListener(this);

        checkBox.setOnCheckedChangeListener(this);
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

    protected void save_app_info()
    {
        AppInfoModel info = new AppInfoModel();
        info.setName_app(name_app.getText().toString());
        info.setName_dev(name_dev.getText().toString());
        info.setDetails(details.getText().toString());
        info.setApp_status(check_app_status ? 1 : 0);

        dao = new AppInfoDAO(getApplicationContext());

        if(edit_ENABLED)
        {
            info.setApp_info_id(this.info.getApp_info_id());

            persist_OK = dao.update(info);

        } else {

            persist_OK = dao.persist(info);
        }

        if(persist_OK)
        {
            intent = new Intent();
            intent.putExtra("item", info);

            setResult(RESULT_OK, intent);

            finish();
        }
    }
}
