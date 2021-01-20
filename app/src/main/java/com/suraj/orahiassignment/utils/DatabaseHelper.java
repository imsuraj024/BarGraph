package com.suraj.orahiassignment.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 6;
    Context context;

    private static final String DATABASE_NAME = "DemoData";

    public static String TABLE_DEMO = "TABLE_DEMO";

    private static final String MONTH = "MONTH";
    private static final String STAT = "STAT";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_DEMO_TABLE = "CREATE TABLE " + TABLE_DEMO + "("
                + MONTH + " TEXT,"
                + STAT + " TEXT" + ")";

        db.execSQL(CREATE_DEMO_TABLE);
        Log.d("Database", "onCreate: Table Batch created");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEMO);
        onCreate(db);
    }

    public int addData(String month, String stat) throws SQLiteException {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String[]args={month,stat};
        String QUERY = "SELECT * FROM  "+TABLE_DEMO + " WHERE MONTH='"+month+"' AND STAT='"+stat+"'; ";
        Cursor cursor = db.rawQuery(QUERY,
                null);

        if (cursor.getCount() > 0) {
            cursor.close();

            return 2;

        } else {

            values.put(MONTH, month);
            values.put(STAT, stat);

            db.insert(TABLE_DEMO, null, values);
            db.close();

            return 1;
        }
    }

    public ArrayList<String[]> getAllData() {
        ArrayList<String[]> dataList = new ArrayList<>();

        String selectQuery = "SELECT * FROM  "+TABLE_DEMO;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {

                String month = (cursor.getString(cursor.getColumnIndex(MONTH)));
                String stat = (cursor.getString(cursor.getColumnIndex(STAT)));
                dataList.add(new String[]{month,stat});
            } while (cursor.moveToNext());
        }

        cursor.close();
        return dataList;
    }

    public int getDataCount(){

        String QUERY = "SELECT * FROM " +TABLE_DEMO ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c =  db.rawQuery(QUERY, null);
        c.moveToFirst();
        int total = c.getCount();
        c.close();
        return total;
    }

    public void deleteData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEMO,null,null);
        db.close();
    }

}
