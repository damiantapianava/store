package mx.unam.store;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class MySqliteHelper extends MySqliteHelperDMO
{
    public MySqliteHelper(Context context)
    {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);

        Log.d(TAG,"MySqliteHelper.onCreate(): OK");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Log.d(TAG, "MySqliteHelper.onUpgrade(): from " + oldVersion+ " to " + newVersion);

        db.execSQL(CREATE_TABLE);
    }
}
