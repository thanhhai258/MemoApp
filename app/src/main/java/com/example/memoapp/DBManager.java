package com.example.memoapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private final String tag = "DatabaseManager";
    public static final String DATABASE_NAME = "memodatabase";
    private static final String TABLE_NAME = "MemoData";
    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String CONTENT = "content";



    private String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            TITLE + " TEXT, " +
            CONTENT + " TEXT )" ;

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d(tag, "database");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        Log.d(tag, "oncreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(tag, "ondugrade");
    }

    public void addMemo(MemoItem memoItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TITLE, memoItem.getmTilte());
        values.put(CONTENT, memoItem.getmContent());

        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d(tag, "add thanh cong");
    }

    public List<MemoItem> getAllMemo() {
        List<MemoItem> listMemo = new ArrayList<>();
        String selectQuery = "SELECT*FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {

                MemoItem memoItem = new MemoItem();
                memoItem.setmId(cursor.getInt(0));
                memoItem.setmTilte(cursor.getString(1));
                memoItem.setmContent(cursor.getString(2));


                listMemo.add(memoItem);
            }
            while (cursor.moveToNext()) ;
        }
        db.close();
        return listMemo;
    }

    public int updateMemo(MemoItem memoItem){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TITLE,memoItem.getmTilte());
        contentValues.put(CONTENT,memoItem.getmContent());
        Log.d(tag, "update thanh cong");
        return db.update(TABLE_NAME,contentValues,ID+"=?",new String[]{String.valueOf(memoItem.getmId())});
    }
    public int deleteStudent(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID+"=?",new String[] {String.valueOf(id)});
    }
}