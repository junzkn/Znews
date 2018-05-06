package com.jun.znews.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChannelDao {

    private MySqLiteOpenHelper mySqliteOpenHelper;
    public ChannelDao(Context context){
        mySqliteOpenHelper = new MySqLiteOpenHelper(context);
    }
//    public void add(ChannelBean bean){
//        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
//        db.beginTransaction();
//        try{
//            db.execSQL("insert into channel(channelNAme,channelID) values(?,?);", new Object[]{bean.name,bean.id,});
//        }finally {
//            db.endTransaction();
//        }
//        db.close();
//
//    }

    public void del(String name){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        db.execSQL("delete from channel where name=?;", new Object[]{name});
        db.close();
    }


    public void query(String name){
        SQLiteDatabase db = mySqliteOpenHelper.getReadableDatabase();
        //查询语句：cursor游标
        Cursor cursor = db.rawQuery("select _id, name,phone from channel where name = ?", new String []{name});
        //解析Cursor中的数据
        if(cursor != null && cursor.getCount() >0){//判断cursor中是否存在数据
            //循环遍历结果集，获取每一行的内容
            while(cursor.moveToNext()){//条件，游标能否定位到下一行
                //获取数据
                int id = cursor.getInt(0);
                String name_str = cursor.getString(1);
                String phone = cursor.getString(2);
                System.out.println("_id:"+id+";name:"+name_str+";phone:"+phone);
            }
            cursor.close();//关闭结果集
        }
        //关闭数据库对象
        db.close();
    }



    private class MySqLiteOpenHelper extends SQLiteOpenHelper {

        public MySqLiteOpenHelper(Context context) {
            super(context, "info.db", null,1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table channel (channelName varchar(20),channelID varchar(20))");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }

    }
}
