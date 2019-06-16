package com.hfad.dzienniczekseniora.asharedPrefereces;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.hfad.dzienniczekseniora.R;

public class BMISharedPreferences {
    private Context context;
    private android.content.SharedPreferences preferences;
    private static final String PREFERENCES_NAME = "heightReferenceValues";
    private static final String PREFERENCES_HEIGHT= "height";
    private static final String PREFERENCES_SEX = "SEX";


    public BMISharedPreferences(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
    }

    /**
     * Method saves data in sharePreferences
     *
     * @param height
     */
    public void saveData(double height,String sex) {
        android.content.SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putFloat(PREFERENCES_HEIGHT, (float)height);
        preferencesEditor.putString(PREFERENCES_SEX, sex);
        preferencesEditor.commit();
    }
    //TODO zmienić R.integer.height wartosc
    public float getHeight() {
        return preferences.getFloat(PREFERENCES_HEIGHT, context.getResources().getInteger(R.integer.height));
    }
    public String getSex() {
        return preferences.getString(PREFERENCES_SEX, context.getResources().getString(R.string.sex));
    }


    public void removeHeight() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.clear().commit();
    }
}
