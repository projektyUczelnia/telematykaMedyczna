package com.hfad.dzienniczekseniora.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Class controlls database
 */
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

    /**
     * Method puts all data into List
     * @param res
     * @return
     */
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

    public List getTemperature30Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.TEMPERATURE.returnTableConstValues().get(0) + ";", null);
        return putValuesToList(res);
    }

    public List getWeight30Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.WEIGHT.returnTableConstValues().get(0) + ";", null);
        return putValuesToList(res);
    }

    public List getPressure30Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.PRESSURE.returnTableConstValues().get(0) + ";", null);
        return putValuesToList(res);
    }

    public List getGlucose30Data() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.GLUCOSE.returnTableConstValues().get(0) +";", null);
        return putValuesToList(res);
    }
    public List getAllWeight() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.WEIGHT.returnTableConstValues().get(0),null);
        return putValuesToList(res);
    }

    public List getAllVisitsFuture() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + EnumTable.VISIT.returnTableConstValues().get(0) +";", null);
        return putValuesToList(res);
    }

    public void getDeleteWeight() {
        db.execSQL("delete from "+ EnumTable.WEIGHT.returnTableConstValues().get(0));
    }public void getDeleteGlucose() {
        db.execSQL("delete from "+ EnumTable.GLUCOSE.returnTableConstValues().get(0));
    }public void getDeleteTemperature() {
        db.execSQL("delete from "+ EnumTable.TEMPERATURE.returnTableConstValues().get(0));
    }public void getDeleteOtherData() {
        db.execSQL("delete from "+ EnumTable.OTHER.returnTableConstValues().get(0));
    }public void getDeleteVisits() {
        db.execSQL("delete from "+ EnumTable.VISIT.returnTableConstValues().get(0));
    }public void getDeletePressure() {
        db.execSQL("delete from "+ EnumTable.PRESSURE.returnTableConstValues().get(0));
    }

    /**
     * Method shows in log all data from dbList
     *
     * @param dbList
     */
    //TODO wyświetla w logach wszystkie dane mysle ze moze sie przydac
    public void CheckIfAllDataFromTableApears(List dbList) {
        List data = null;
        if (dbList != null) {
            data = dbList;
        }
        if (data != null) {
            for (int i = 0; i < data.size(); i++) {
                List col1 = (List) data.get(i);
                Log.d("data", (String) col1.get(0) + "\n" + col1.get(1) + "\n" + col1.get(2) + "\n" + col1.get(3));
            }
            Log.d("data", "null");
        }
    }
}
