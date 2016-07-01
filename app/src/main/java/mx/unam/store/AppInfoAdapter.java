package mx.unam.store;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AppInfoAdapter extends ArrayAdapter<AppInfoModel>
{
    private TextView name_app;
    private TextView name_dev;
    private TextView details;
    private ImageView img;

    private CheckBox checkBox;

    public AppInfoAdapter(Context context, List<AppInfoModel> list)
    {
        super(context, 0, list);
    }

    @Nullable
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        if(convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_app_info, parent, false);
        }

        name_app = (TextView)  convertView.findViewById(R.id.tv_name_app);
        name_dev = (TextView)  convertView.findViewById(R.id.tv_name_developer);
         details = (TextView)  convertView.findViewById(R.id.tv_app_details);
             img = (ImageView) convertView.findViewById(R.id.iv_app_ic);
        checkBox = (CheckBox ) convertView.findViewById(R.id.adapter_app_status);

        AppInfoModel info = getItem(position);

        name_app.setText(info.getName_app());
        name_dev.setText(info.getName_dev());
         details.setText(info.getDetails());

        checkBox.setChecked(info.getApp_status() > 0 ? true : false);

        return convertView;
    }
}
