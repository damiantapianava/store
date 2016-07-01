package mx.unam.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class AddActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener
{
    private ActionBar action_bar;

    private EditText name_app;
    private EditText name_dev;
    private EditText details;
    private CheckBox checkBox;

    private AppInfoDAO dao;
    private AppInfoModel info;

    private boolean check_app_status;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        name_app = (EditText) findViewById(R.id.et_name_app);
        name_dev = (EditText) findViewById(R.id.et_name_developer);
        details  = (EditText) findViewById(R.id.et_app_details);
        checkBox = (CheckBox) findViewById(R.id.check_app_status);

        findViewById(R.id.btnSave).setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        action_bar = getSupportActionBar();

        action_bar.setTitle(R.string.str_toolbar_title);
        action_bar.setDisplayHomeAsUpEnabled(true);
        action_bar.setHomeButtonEnabled(true);
    }

    @Override
    public void onClick(View v)
    {
        Intent i = new Intent();
        i.putExtra("key_name_app", name_app.getText().toString());
        i.putExtra("key_name_dev", name_dev.getText().toString());
        i.putExtra("key_details",   details.getText().toString());
        i.putExtra("check_app_status",   check_app_status);

        info = new AppInfoModel();
        info.setName_app(name_app.getText().toString());
        info.setName_dev(name_dev.getText().toString());
        info.setDetails(details.getText().toString());
        info.setApp_status(check_app_status ? 1 : 0);

        dao = new AppInfoDAO(getApplicationContext());

        dao.persist(info);

        setResult(RESULT_OK, i);

        finish();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        check_app_status = isChecked;
    }
}
