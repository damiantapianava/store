package mx.unam.store;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public abstract class MySqliteHelperDMO extends SQLiteOpenHelper
{
    public static final String TAG = "store_tag";

    public static final String COLUMN_ID = BaseColumns._ID;

    public static final String   DATABASE_NAME = "unam_app_manager";
    public static final String      TABLE_NAME = "gn_app_info";
    public static final String COLUMN_NAME_APP = "name_app";
    public static final String COLUMN_NAME_DEV = "name_developer";
    public static final String COLUMN_DETAIL   = "app_detail";
    public static final String COLUMN_STATUS   = "app_status";

    public final static int DATABASE_VERSION = 1;

    protected static final String CREATE_TABLE  = "create table IF NOT EXISTS " +
            TABLE_NAME      + " ( " +
            COLUMN_ID       + " integer primary key autoincrement,"+
            COLUMN_NAME_APP + " text not null,"+
            COLUMN_NAME_DEV + " text not null,"+
            COLUMN_DETAIL   + " text not null,"+
            COLUMN_STATUS   + " integer not null)";

    public MySqliteHelperDMO(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
