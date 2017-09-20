package com.example.vova.usersbook.models.engines;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.vova.usersbook.constants.DBConstans;
import com.example.vova.usersbook.constants.DBConstans.UsersTable;
import com.example.vova.usersbook.models.UserInfo;

import java.util.ArrayList;

public class UserInfoEngine extends BaseEngine {

    public UserInfoEngine(Context context) {
        super(context, UsersTable.TABLE_NAME);
    }

    public ArrayList<UserInfo> getAllUsers() {
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        SQLiteDatabase database = getReadable();
        Cursor cursor = database.query(getStrTableName(), null, null, null, null, null, null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    UserInfo userInfo = new UserInfo(cursor);
                    userInfos.add(userInfo);
                } while (cursor.moveToNext());
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            database.close();
        }
        return userInfos;
    }

    public void removeUserById(long id) {
        SQLiteDatabase database = getWritable();
        String request = UsersTable.Cols.USER_ID + "=?";
        String arrArg[] = new String[]{Long.toString(id)};
        database.delete(getStrTableName(), request, arrArg);
        database.close();
    }

    public long addUser(UserInfo userInfo) {
        SQLiteDatabase database = getWritable();
        long id = database.insert(getStrTableName(), null, userInfo.getContentValues());
        database.close();
        return id;
    }

    public void updateUser(UserInfo userInfo) {
        SQLiteDatabase database = getWritable();
        String request = UsersTable.Cols.USER_ID + "=?";
        String arrArgs[] = new String[] {Long.toString(userInfo.getId())};
        database.update(getStrTableName(), userInfo.getContentValues(), request, arrArgs);
        database.close();
    }

}
