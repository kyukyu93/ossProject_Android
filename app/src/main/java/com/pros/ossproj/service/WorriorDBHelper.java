package com.pros.ossproj.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by ruwn on 2017-03-09.
 */

public class WorriorDBHelper  extends SQLiteOpenHelper {

    private Context context;
    WorriorLocation worriorLocation = new WorriorLocation();

    public WorriorDBHelper(Context context, String worrior_time, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, worrior_time, factory, version);
        this.context = context;
    }

    public ArrayList<String> readWorriorDatabaseLocationDate() {
        ArrayList<String> arrDate = new ArrayList<String>();
        String str1;
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + "WR_LOCATION";// + " WHERE " + "DETAIL";
        Cursor cursor = db.rawQuery(selectQuery, null);
        WorriorLocation worriorLocation = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                worriorLocation = new WorriorLocation();
                worriorLocation.set_id(cursor.getInt(0));
                worriorLocation.setTime(cursor.getString(1));
                worriorLocation.setAccuracy(cursor.getString(2));
                worriorLocation.setLatitude(cursor.getString(3));
                worriorLocation.setLongitude(cursor.getString(4));
                Log.d("readWorriorDatabase", "ID " + worriorLocation.get_id());
                Log.d("readWorriorDatabase", " TIME " + worriorLocation.getTime());
                Log.d("readWorriorDatabase", " ACCURACY " + worriorLocation.getAccuracy());
                Log.d("readWorriorDatabase", " LATITUDE " + worriorLocation.getLatitude());
                Log.d("readWorriorDatabase", " LONGITUDE " + worriorLocation.getLongitude());
                arrDate.add(worriorLocation.getTime());

            } while (cursor.moveToNext());
        }
        return arrDate;
    }
    public ArrayList<String> readWorriorDatabaseLocationAddress() {
        ArrayList<String> arrAddress = new ArrayList<String>();
        String str1;
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + "WR_LOCATION";// + " WHERE " + "DETAIL";
        Cursor cursor = db.rawQuery(selectQuery, null);
        WorriorLocation worriorLocation = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                worriorLocation = new WorriorLocation();
                worriorLocation.set_id(cursor.getInt(0));
                worriorLocation.setTime(cursor.getString(1));
                worriorLocation.setAccuracy(cursor.getString(2));
                worriorLocation.setLatitude(cursor.getString(3));
                worriorLocation.setLongitude(cursor.getString(4));
                Log.d("readWorriorDatabase", "ID " + worriorLocation.get_id());
                Log.d("readWorriorDatabase", " TIME " + worriorLocation.getTime());
                Log.d("readWorriorDatabase", " ACCURACY " + worriorLocation.getAccuracy());
                Log.d("readWorriorDatabase", " LATITUDE " + worriorLocation.getLatitude());
                Log.d("readWorriorDatabase", " LONGITUDE " + worriorLocation.getLongitude());
                arrAddress.add(worriorLocation.getLatitude() +", " + worriorLocation.getLongitude());

            } while (cursor.moveToNext());
        }
        return arrAddress;
    }
    public void readWorriorDatabaseLocation() {
        String str1;
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + "WR_LOCATION";// + " WHERE " + "DETAIL";
        Cursor cursor = db.rawQuery(selectQuery, null);
        WorriorLocation lionaLocation = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                worriorLocation = new WorriorLocation();
                worriorLocation.set_id(cursor.getInt(0));
                worriorLocation.setTime(cursor.getString(1));
                worriorLocation.setAccuracy(cursor.getString(2));
                worriorLocation.setLatitude(cursor.getString(3));
                worriorLocation.setLongitude(cursor.getString(4));
                Log.d("readWorriorDatabase", "ID " + worriorLocation.get_id());
                Log.d("readWorriorDatabase", " TIME " + worriorLocation.getTime());
                Log.d("readWorriorDatabase", " ACCURACY " + worriorLocation.getAccuracy());
                Log.d("readWorriorDatabase", " LATITUDE " + worriorLocation.getLatitude());
                Log.d("readWorriorDatabase", " LONGITUDE " + worriorLocation.getLongitude());

            } while (cursor.moveToNext());
        }
    }
    public void addWorriorLocationData(WorriorLocation worriorLocation) {
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO WR_LOCATION ( ");
        sb.append(" TIME, ACCURACY, LATITUDE, LONGITUDE ) ");
        sb.append(" VALUES ( ?, ?, ?, ? ) ");

        db.execSQL(sb.toString(), new Object[]{
                worriorLocation.getTime(),
                worriorLocation.getAccuracy(),
                worriorLocation.getLatitude(),
                worriorLocation.getLongitude()
        });
        Toast.makeText(context, "Insert 완료", Toast.LENGTH_SHORT).show();

        try {
            WorriorAPI worriorAPI = new WorriorAPI();
            Log.d("###", "msg" + worriorAPI.execute(worriorLocation.getTime(), worriorLocation.getAccuracy(), worriorLocation.getLatitude(), worriorLocation.getLongitude()).get());
        }
        catch (Exception e) {
            Log.d("###", "exception" + e.getMessage());
        }

        readWorriorDatabaseLocation();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE WR_LOCATION (");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" TIME TEXT, ");
        sb.append(" ACCURACY TEXT, ");
        sb.append(" LATITUDE TEXT, ");
        sb.append(" LONGITUDE TEXT ) ");

        db.execSQL(sb.toString());

        Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();
    }

    // 버전이 올라가서 Table 구조가 변경되었을 시
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Toast.makeText(context, "버전이 올라갔습니다.", Toast.LENGTH_SHORT).show();
    }
    public void testDB() {
        SQLiteDatabase db  = getReadableDatabase();
    }
}