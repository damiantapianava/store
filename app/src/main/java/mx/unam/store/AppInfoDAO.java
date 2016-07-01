package mx.unam.store;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class AppInfoDAO
{
    private MySqliteHelper helper;

    private SQLiteDatabase db;

    private ContentValues contentValues;

    public AppInfoDAO(Context context)
    {
        helper = new MySqliteHelper(context);

        db = helper.getWritableDatabase();
    }

    public boolean persist(AppInfoModel info)
    {
        contentValues = new ContentValues();
        contentValues.put(MySqliteHelper.COLUMN_NAME_APP, info.getName_app());
        contentValues.put(MySqliteHelper.COLUMN_NAME_DEV, info.getName_dev());
        contentValues.put(MySqliteHelper.COLUMN_DETAIL,   info.getDetails());
        contentValues.put(MySqliteHelper.COLUMN_STATUS,   info.getApp_status());

        long row_id = db.insert(MySqliteHelper.TABLE_NAME, null, contentValues);

        if(row_id > 0)
        {
            return true;

        }  else {

            return false;
        }
    }

    public List<AppInfoModel> getLista_AppInfo()
    {
        List<AppInfoModel> modelItemList = new ArrayList<>();

        Cursor cursor = db.query(MySqliteHelper.TABLE_NAME,null,null,null,null,null,null);

        while (cursor.moveToNext())
        {
            int id          = cursor.getInt   (cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_ID));
            int app_status  = cursor.getInt   (cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_STATUS));
            String name_app = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_NAME_APP));
            String name_dev = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_NAME_DEV));
            String details  = cursor.getString(cursor.getColumnIndexOrThrow(MySqliteHelper.COLUMN_DETAIL));

            AppInfoModel info = new AppInfoModel();
            info.setName_app(name_app);
            info.setName_dev(name_dev);
            info.setDetails(details);
            info.setApp_status(app_status);

            modelItemList.add(info);
        }

        return modelItemList;
    }
}
