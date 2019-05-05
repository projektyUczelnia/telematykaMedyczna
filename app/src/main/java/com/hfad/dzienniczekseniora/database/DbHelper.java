package com.hfad.dzienniczekseniora.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;


public class DbHelper extends SQLiteOpenHelper {


    public DbHelper(Context context) {
        super(context, "dzienniczekSenioraDb", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableCommandCerator(EnumTable.TEMPERATURE.returnTableConstValues()));
        db.execSQL(createTableCommandCerator(EnumTable.GLUCOSE.returnTableConstValues()));
        db.execSQL(createTableCommandCerator(EnumTable.PRESSURE.returnTableConstValues()));
        db.execSQL(createTableCommandCerator(EnumTable.WEIGHT.returnTableConstValues()));
        db.execSQL(createTableCommandCerator(EnumTable.VISIT.returnTableConstValues()));
        db.execSQL(createTableCommandCerator(EnumTable.OTHER.returnTableConstValues()));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + EnumTable.TEMPERATURE.returnTableConstValues().get(0) + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + EnumTable.WEIGHT.returnTableConstValues().get(0) + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + EnumTable.GLUCOSE.returnTableConstValues().get(0) + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + EnumTable.PRESSURE.returnTableConstValues().get(0) + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + EnumTable.OTHER.returnTableConstValues().get(0) + "'");
        db.execSQL("DROP TABLE IF EXISTS '" + EnumTable.VISIT.returnTableConstValues().get(0) + "'");
        onCreate(db);
    }

    private String createTableCommandCerator(List columnsList) {
        String command = "CREATE TABLE " + columnsList.get(0) + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + columnsList.get(1) + " TEXT, " + columnsList.get(2) + " TEXT, " + columnsList.get(3) + " TEXT);";
        return command;
    }

    public void insert_data(List EnumTableClassList, String date, String time, String value) {
    }

    public List getTempData(String date) {
        return null;
    }

    public List getGlucoseData(String date) {
        return null;
    }

    public List getPressureData(String date) {
        return null;
    }

    public List getWeightData(String date) {
        return null;
    }

    public List getVisitData(String date) {
        return null;
    }

    public List getOtherData(String date) {
        return null;
    }

    public List getTemperature30Data()  {
        return null;
    }

    public List getWeight30Data()  {
        return null;
    }
    public List getPressure30Data()  {
        return null;
    }
    public List getGlucose30Data()  {
        return null;
    }

    public void getDelete(){}

}
