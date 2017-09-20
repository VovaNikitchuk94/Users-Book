package com.example.vova.usersbook.models.engines;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.vova.usersbook.constants.DBConstans;
import com.example.vova.usersbook.database.DBHelper;

public abstract class BaseEngine {

//    private Context mContext;
    private DBHelper mDBHelper;
    private String mStrTableName;

    public BaseEngine(Context context, String strTableName) {
        mDBHelper = new DBHelper(context);
        mStrTableName = strTableName;
    }

    public SQLiteDatabase getWritable() {
        return mDBHelper.getWritableDatabase();
    }

    public SQLiteDatabase getReadable() {
        return mDBHelper.getReadableDatabase();
    }

    public String getStrTableName() {
        return mStrTableName;
    }
}
