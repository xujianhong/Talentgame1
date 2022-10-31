package com.daomingedu.talentgame.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import timber.log.Timber;

public class BaseDataHelper extends SQLiteOpenHelper {
    static String DATABASENAME = "Talentgame_video";
    static int VERSION = 3;

    // 本地作品
    static String CONTACT = "create table contact(filepath varchar(255) not null primary key ," +
            "fileName varchar(255) not null, " +
            "folderName varchar(255) not null)";



    public BaseDataHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {

        arg0.execSQL(CONTACT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        Timber.e("当前SQL版本："+arg1);
//        switch (arg1) {//判断版本
//
//            case 1://版本1时CONTACT没有shareId
//                arg0.execSQL("alter table contact ADD shareId varchar(255)");
//                arg0.execSQL("alter table contact ADD scoreId varchar(255)");
//                arg0.execSQL("alter table contact ADD scoreName varchar(255)");
//                arg0.execSQL("alter table contact ADD totalScore integer");
//
//                break;
//            case 2:
//                arg0.execSQL(UPLOAD);
//                break;
//        }

    }

}
