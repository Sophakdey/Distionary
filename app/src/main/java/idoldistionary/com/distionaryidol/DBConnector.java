package idoldistionary.com.distionaryidol;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

/**
 * Created by Administrator on 20/11/2016.
 */

public class DBConnector extends SQLiteAssetHelper {


    private static final String MY_DB_NAME = "dictionary1.db";
    private static final int DATABASE_VERSION = 2;

    public DBConnector(Context context) {
        super(context,MY_DB_NAME,null,DATABASE_VERSION);
    }
}

