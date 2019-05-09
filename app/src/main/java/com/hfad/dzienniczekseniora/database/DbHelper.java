package com.hfad.dzienniczekseniora.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

/**
 * Class creates ned database and tables of sqlite database
 */
public class DbHelper extends SQLiteOpenHelper {

    /**
     * Method creates db
     * @param context
     */
    public DbHelper(Context context) {
        super(context, "dzienniczekSenioraDb", null, 1);
    }

    /**
     * Method creates all Dables in database
     * @param db
     */
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

    /**
     * Method creates sqlite command to create tables in database
     * @param columnsList
     * @return
     */
    private String createTableCommandCerator(List columnsList) {
        String command = "CREATE TABLE " + columnsList.get(0) + "(ID INTEGER PRIMARY KEY AUTOINCREMENT," + columnsList.get(1) + " TEXT, " + columnsList.get(2) + " TEXT, " + columnsList.get(3) + " TEXT);";
        return command;
    }

    /**
     * Method inserts data into database
     * @param EnumTableClassList
     * @param date
     * @param time
     * @param value
     */
    public void insert_data(List EnumTableClassList, String date, String time, String value) {
    }

    /**
     * Method returns data from Temperature table
     * @param date
     * @return
     */
    public List getTempData(String date) {
        return null;
    }

    /**
     * Method returns data from Glucose table
     * @param date
     * @return
     */
    public List getGlucoseData(String date) {
        return null;
    }

    /**
     * Method returns data from Pressure table
     * @param date
     * @return
     */
    public List getPressureData(String date) {
        return null;
    }

    /**
     * Method returns data from Weight table
     * @param date
     * @return
     */
    public List getWeightData(String date) {
        return null;
    }

    /**
     * Method returns data from Visit table
     * @param date
     * @return
     */
    public List getVisitData(String date) {
        return null;
    }

    /**
     * Method returns data from OtherData table
     * @param date
     * @return
     */
    public List getOtherData(String date) {
        return null;
    }

    /**
     * Method returns data from Temperature table for last 30 days
     * @return
     */
    public List getTemperature30Data() {
        return null;
    }

    /**
     * Method returns data from Weight table for last 30 days
     * @return
     */
    public List getWeight30Data() {
        return null;
    }

    /**
     * Method returns data from Pressure table for last 30 days
     * @return
     */
    public List getPressure30Data() {
        return null;
    }

    /**
     * Method returns data from Glucose table for last 30 days
     * @return
     */
    public List getAllVisitsFuture() {
        return null;
    }



    /**
     * Method deletes all data from Weight table
     */
    public void getDeleteWeight() {
    }

    /**
     * Method deletes all data from Visit table
     */
    public void getDeleteVisits() {
    }

    /**
     * Method deletes all data from OtherData table
     */
    public void getDeleteOtherData() {
    }

    /**
     * Method deletes all data from Temperature table
     */
    public void getDeleteTemperature() {
    }

    /**
     * Method deletes all data from Pressure table
     */
    public void getDeletePressure() {
    }

    /**
     * Method deletes all data from Glucose table
     */
    public void getDeleteGlucose() {
    }



//TODO delete that

    /**
     * Method returns All data from Weight table
     * @return
     */
    public List getAllWeight() {
        return null;
    }
}
