package mx.unam.store;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

public class AddActivity extends AddActivityDMO
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add);

        init_view();
        init_toolbar();

        intent = getIntent();

        if(intent != null)
        {
            info = (AppInfoModel) intent.getExtras().getSerializable("item");

            name_app.setText(info.getName_app());
            name_dev.setText(info.getName_dev());
            details.setText(info.getDetails());
            checkBox.setChecked(info.getApp_status() > 0 ? true : false);

            btn_save.setVisibility(View.GONE);
            btn_edit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btnSave:
                edit_ENABLED = false;
                break;

            case R.id.btn_edit:
                edit_ENABLED = true;
                break;
        }

        save_app_info();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
    {
        check_app_status = isChecked;
    }
}
