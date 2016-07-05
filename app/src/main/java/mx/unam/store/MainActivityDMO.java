package mx.unam.store;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public abstract class MainActivityDMO extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    protected static final int REQUEST_CODE_SECOND_ACTIVITY = 1;

    protected Context context;

    protected ActionBar action_bar;
    protected Toolbar toolbar;
    protected TextView empty_msg;

    protected ListView lista_app_info;

    protected Intent intent;

    protected AppInfoDAO dao;
    protected AppInfoAdapter adapter;
    protected AppInfoModel info;

    protected List<AppInfoModel> lista_app_info_model;

    protected void init_lista_app()
    {
        lista_app_info_model = dao.getLista_AppInfo();

        adapter = new AppInfoAdapter(context, lista_app_info_model);

        lista_app_info.setAdapter(adapter);

        if(lista_app_info_model.size() > 0)
        {
            empty_msg.setVisibility(View.GONE);

        } else {

            empty_msg.setVisibility(View.VISIBLE);
        }
    }
}
