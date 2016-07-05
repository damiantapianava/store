package mx.unam.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends MainActivityDMO
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

               toolbar = (Toolbar)  findViewById(R.id.toolbar);
        lista_app_info = (ListView) findViewById(R.id.lista_app_info);
             empty_msg = (TextView) findViewById(R.id.tv_empty_list_msg);

        lista_app_info.setOnItemClickListener(this);

        setSupportActionBar(toolbar);

        action_bar = getSupportActionBar();
        action_bar.setTitle(R.string.str_toolbar_title);
        action_bar.setIcon(android.R.drawable.sym_def_app_icon);
        action_bar.setHomeButtonEnabled(true);

        context = getApplicationContext();

        dao = new AppInfoDAO(context);

        init_lista_app();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_add_entry:
                intent = new Intent(context, AddActivity.class);

                startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(REQUEST_CODE_SECOND_ACTIVITY == requestCode && resultCode == RESULT_OK)
        {
            init_lista_app();

        } else {

            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {
        adapter = (AppInfoAdapter) parent.getAdapter();

        info = adapter.getItem(position);

        intent = new Intent(context, DetailActivity.class);
        intent.putExtra("item", info);

        startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
    }
}
