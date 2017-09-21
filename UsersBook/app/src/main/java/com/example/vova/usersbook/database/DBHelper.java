package com.example.vova.usersbook.database;

        import android.content.Context;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;

        import com.example.vova.usersbook.constants.DBConstans;
        import com.example.vova.usersbook.constants.DBConstans.UsersTable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, DBConstans.DB_NAME, null, DBConstans.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + UsersTable.TABLE_NAME
                + " (" + UsersTable.Cols.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + UsersTable.Cols.USER_NAME + " TEXT, "
                + UsersTable.Cols.USER_SURNAME + " TEXT, "
                + UsersTable.Cols.USER_BDAY + " TEXT); ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
