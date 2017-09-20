package com.example.vova.usersbook.models;

import android.content.ContentValues;

public abstract class BaseEntity {

    private long mId = -1;

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public abstract ContentValues getContentValues();
}
