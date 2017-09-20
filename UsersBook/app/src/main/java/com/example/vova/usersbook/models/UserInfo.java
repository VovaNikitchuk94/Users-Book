package com.example.vova.usersbook.models;

import android.content.ContentValues;
import android.database.Cursor;

import com.example.vova.usersbook.constants.DBConstans.UsersTable;

public class UserInfo extends BaseEntity {

    private String mUserName;
    private String mUserSurname;
    private long mUserBDay;

    public UserInfo(String userName, String userSurname, long userBDay) {
        mUserName = userName;
        mUserSurname = userSurname;
        mUserBDay = userBDay;
    }

    public UserInfo(Cursor cursor) {
        setId(cursor.getLong(cursor.getColumnIndex(UsersTable.Cols.USER_ID)));
        mUserName = cursor.getString(cursor.getColumnIndex(UsersTable.Cols.USER_NAME));
        mUserSurname = cursor.getString(cursor.getColumnIndex(UsersTable.Cols.USER_SURNAME));
        mUserBDay = cursor.getLong(cursor.getColumnIndex(UsersTable.Cols.USER_BDAY));
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getUserSurname() {
        return mUserSurname;
    }

    public void setUserSurname(String userSurname) {
        mUserSurname = userSurname;
    }

    public long getUserBDay() {
        return mUserBDay;
    }

    public void setUserBDay(long userBDay) {
        mUserBDay = userBDay;
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(UsersTable.Cols.USER_NAME, getUserName());
        values.put(UsersTable.Cols.USER_SURNAME, getUserSurname());
        values.put(UsersTable.Cols.USER_BDAY, getUserBDay());
        return values;
    }
}
