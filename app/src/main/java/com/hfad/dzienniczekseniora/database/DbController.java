package com.hfad.dzienniczekseniora.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DbController extends DbHelper {
    public DbController(Context context) {
        super(context);
    }

    private SQLiteDatabase db = this.getWritableDatabase();

    @Override
    public void insert_data(List EnumTableClassList, String date, String time, String value) {
        ContentValues values = new ContentValues();
        values.put((String) EnumTableClassList.get(1), date);
        values.put((String) EnumTableClassList.get(2), time);
        values.put((String) EnumTableClassList.get(3), value);
        long RESULT = db.insert((String) EnumTableClassList.get(0), null, values);
        if (RESULT == -1) {
            Log.d("data insert check", " Data not inserted");
        } else {
            Log.d("data insert check", "Data inserted");
        }
    }

    private List putValuesToList(Cursor res) {
        List<List> data = new ArrayList<>();
        if (res.getCount() == 0) {
            return null;
        }
        while (res.moveToNext()) {
            List<String> temperatureData = new ArrayList<>();
            temperatureData.add(res.getString(0));
            temperatureData.add(res.getString(1));
            temperatureData.add(res.getString(2));
            temperatureData.add(res.getString(3));
            data.add(temperatureData);
        }
        return data;
    }

    public List getTempData(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.TEMPERATURE.returnTableConstValues().get(0) + " WHERE " + EnumTable.TEMPERATURE.returnTableConstValues().get(1) + " LIKE '" + date + "';", null);
        return putValuesToList(res);
    }

    public List getGlucoseData(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.GLUCOSE.returnTableConstValues().get(0) + " WHERE " + EnumTable.GLUCOSE.returnTableConstValues().get(1) + " LIKE '" + date + "';", null);
        return putValuesToList(res);
    }

    public List getPressureData(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.PRESSURE.returnTableConstValues().get(0) + " WHERE " + EnumTable.PRESSURE.returnTableConstValues().get(1) + " LIKE '" + date + "';", null);
        return putValuesToList(res);
    }

    public List getWeightData(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.WEIGHT.returnTableConstValues().get(0) + " WHERE " + EnumTable.WEIGHT.returnTableConstValues().get(1) + " LIKE '" + date + "';", null);
        return putValuesToList(res);
    }

    public List getVisitData(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.VISIT.returnTableConstValues().get(0) + " WHERE " + EnumTable.VISIT.returnTableConstValues().get(1) + " LIKE '" + date + "';", null);
        return putValuesToList(res);
    }

    public List getOtherData(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.OTHER.returnTableConstValues().get(0) + " WHERE " + EnumTable.OTHER.returnTableConstValues().get(1) + " LIKE '" + date + "';", null);
        return putValuesToList(res);
    }

}