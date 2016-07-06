package mx.unam.store;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class DetailActivity extends DetailActivityDMO
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        init_view();
        init_toolbar();

        intent = getIntent();

        init_intent_info(intent);

        menu_edit_ENABLED = true;

        unistalled.setVisibility(View.GONE);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.btn_uninstall:
                init_uninstall();

                IntentFilter filter = new IntentFilter();
                filter.addAction(ServiceNotification.ACTION_UNINSTALL);

                //registerReceiver(broadcastReceiver, filter);
                break;

            case R.id.btn_update:
                init_update();
                break;
        }
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver()
    {
        @Override
        public void onReceive(Context context, Intent intent)
        {
            int uninstall_status = intent.getExtras().getInt("uninstall_status");

            switch (uninstall_status)
            {
                case ServiceNotification.STARTED:
                    findViewById(R.id.btn_uninstall).setVisibility(View.GONE);
                    findViewById(R.id.btn_open).setVisibility(View.GONE);
                    findViewById(R.id.btn_update).setVisibility(View.GONE);
                    break;

                case ServiceNotification.COMPLETED:
                    menu_edit_ENABLED = false;
                    unistalled.setVisibility(View.VISIBLE);
                    break;
            }

            stopService(new Intent(getApplicationContext(), ServiceNotification.class));
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_activity_detail, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_edit_entry:
                if(menu_edit_ENABLED) {
                    intent = new Intent(getApplicationContext(), AddActivity.class);
                    intent.putExtra("item", info);

                    startActivityForResult(intent, REQUEST_CODE_EDIT_ACTIVITY);
                }

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (REQUEST_CODE_EDIT_ACTIVITY == requestCode && resultCode == RESULT_OK)
        {
            init_intent_info(data);
        }
    }
}
