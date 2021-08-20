package com.pros.ossproj.service;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class PatternDBHelper extends SQLiteOpenHelper {

    private Context context;
    WorriorPattern worriorPattern = new WorriorPattern();

    public PatternDBHelper(Context context, String act_date, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, act_date, factory, version);
        this.context = context;
    }

    public ArrayList<String> readWorriorDatabasePatternTime() {
        String str1;
        ArrayList<String> arrPatternTime = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + "PATTERN_TABLE";// + " WHERE " + "DETAIL";
        Cursor cursor = db.rawQuery(selectQuery, null);
        WorriorPattern worriorPattern = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                worriorPattern = new WorriorPattern();
                worriorPattern.set_id(cursor.getInt(0));
                worriorPattern.setAct_date(cursor.getString(1));
                worriorPattern.setAct_do(cursor.getString(2));
                worriorPattern.setAct_location(cursor.getString(3));
                //System.out.print("ID " + lionaPattern.get_id());
                //System.out.print(" DATE " + lionaPattern.getDate());
                //System.out.print(" DO " + lionaPattern.getDo());
                //System.out.println(" LOCATION " + lionaPattern.getLocation());
                arrPatternTime.add(worriorPattern.getAct_date());

            } while (cursor.moveToNext());
        }
        return arrPatternTime;
    }
    public ArrayList<String> readWorriorDatabasePatternAction() {
        String str1;
        ArrayList<String> arrPatternAction = new ArrayList<String>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + "PATTERN_TABLE";// + " WHERE " + "DETAIL";
        Cursor cursor = db.rawQuery(selectQuery, null);
        WorriorPattern worriorPattern = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                worriorPattern = new WorriorPattern();
                worriorPattern.set_id(cursor.getInt(0));
                worriorPattern.setAct_date(cursor.getString(1));
                worriorPattern.setAct_do(cursor.getString(2));
                worriorPattern.setAct_location(cursor.getString(3));
                //System.out.print("ID " + worriorPattern.get_id());
                //System.out.print(" DATE " + worriorPattern.getDate());
                //System.out.print(" DO " + worriorPattern.getDo());
                //System.out.println(" LOCATION " + worriorPattern.getLocation());
                arrPatternAction.add(worriorPattern.getAct_do());

            } while (cursor.moveToNext());
        }
        return arrPatternAction;
    }
    public void readWorriorDatabasePattern() {
        String str1;
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + "PATTERN_TABLE";// + " WHERE " + "DETAIL";
        Cursor cursor = db.rawQuery(selectQuery, null);
        WorriorPattern worriorPattern = null;
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                worriorPattern = new WorriorPattern();
                worriorPattern.set_id(cursor.getInt(0));
                worriorPattern.setAct_date(cursor.getString(1));
                worriorPattern.setAct_do(cursor.getString(2));
                worriorPattern.setAct_location(cursor.getString(3));
                System.out.print("ID " + worriorPattern.get_id());
                System.out.print(" DATE " + worriorPattern.getAct_date());
                System.out.print(" DO " + worriorPattern.getAct_do());
                System.out.println(" LOCATION " + worriorPattern.getAct_location());

            } while (cursor.moveToNext());
        }
    }

    public void addWorriorPatternData(WorriorPattern lionaPattern) {
        SQLiteDatabase db = getWritableDatabase();
        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO PATTERN_TABLE ( ");
        sb.append(" DATE, DO, LOCATION ) ");
        sb.append(" VALUES ( ?, ?, ? ) ");

        db.execSQL(sb.toString(), new Object[]{
                lionaPattern.getAct_date(),
                lionaPattern.getAct_do(),
                lionaPattern.getAct_location()});
        //Toast.makeText(context, "Insert 완료", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE PATTERN_TABLE (");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" DATE TEXT, ");
        sb.append(" DO TEXT, ");
        sb.append(" LOCATION TEXT ) ");

        db.execSQL(sb.toString());

        //Toast.makeText(context, "Table 생성완료", Toast.LENGTH_SHORT).show();
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
