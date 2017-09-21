package com.example.vova.usersbook.models;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.vova.usersbook.constants.DBConstans.UsersTable;

public class UserInfo extends BaseEntity implements Parcelable {

    private String mUserName;
    private String mUserSurname;
    private String mUserBDay;

    public UserInfo() {
    }

    public UserInfo(String userName, String userSurname, String userBDay) {
        mUserName = userName;
        mUserSurname = userSurname;
        mUserBDay = userBDay;
    }

    public UserInfo(Cursor cursor) {
        setId(cursor.getLong(cursor.getColumnIndex(UsersTable.Cols.USER_ID)));
        mUserName = cursor.getString(cursor.getColumnIndex(UsersTable.Cols.USER_NAME));
        mUserSurname = cursor.getString(cursor.getColumnIndex(UsersTable.Cols.USER_SURNAME));
        mUserBDay = cursor.getString(cursor.getColumnIndex(UsersTable.Cols.USER_BDAY));
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

    public String getUserBDay() {
        return mUserBDay;
    }

    public void setUserBDay(String userBDay) {
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

    protected UserInfo(Parcel in) {
        setId(in.readLong());
        mUserName = in.readString();
        mUserSurname = in.readString();
        mUserBDay = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeString(mUserName);
        dest.writeString(mUserSurname);
        dest.writeString(mUserBDay);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<UserInfo> CREATOR = new Parcelable.Creator<UserInfo>() {
        @Override
        public UserInfo createFromParcel(Parcel in) {
            return new UserInfo(in);
        }

        @Override
        public UserInfo[] newArray(int size) {
            return new UserInfo[size];
        }
    };
}