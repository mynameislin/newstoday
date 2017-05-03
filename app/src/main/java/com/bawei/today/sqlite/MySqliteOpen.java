package com.bawei.today.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 类的用途：
 *
 * @author 林慧强
 * @time 2017/3/24 16:28
 */

public class MySqliteOpen extends SQLiteOpenHelper {
    public MySqliteOpen(Context context) {
        super(context, "newstudy", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table dbguan (_id integer primary key autoincrement , title varchar (100) )");
        db.execSQL(" create table dbshou (_id integer primary key autoincrement , tilte varchar (100) , votecount varchar(100) , ptime varchar(100) , imgsrc varchar(255) , imgsrc1 varchar (255) , imgsrc2 varchar(255) )");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
