package mx.unam.store;

import android.content.Intent;
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
    }

    private void init_intent_info(Intent intent)
    {
        info = (AppInfoModel) intent.getExtras().getSerializable("item");

        name_app.setText(info.getName_app());
        name_dev.setText(info.getName_dev());
        details.setText(info.getDetails());
        app_status.setChecked(info.getApp_status() > 0 ? true : false);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_open:
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
            case R.id.btn_uninstall:
                break;
            case R.id.btn_update:
                break;
        }
    }

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
                intent = new Intent(getApplicationContext(), AddActivity.class);
                intent.putExtra("item", info);

                startActivityForResult(intent, REQUEST_CODE_EDIT_ACTIVITY);

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
