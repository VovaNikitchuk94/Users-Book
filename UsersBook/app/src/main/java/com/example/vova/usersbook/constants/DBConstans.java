package com.example.vova.usersbook.constants;

public class DBConstans {

    public static final String DB_NAME = "user_db";
    public static final int DB_VERSION = 1;

    public static final class UsersTable {
        public static final String TABLE_NAME = "UsersIfo";

        public static final class Cols {
            public static final String USER_ID = "_id";
            public static final String USER_NAME = "_name";
            public static final String USER_SURNAME = "_surname";
            public static final String USER_BDAY = "_bday";
        }
    }

}
