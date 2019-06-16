<<<<<<< HEAD
package com.hfad.dzienniczekseniora.asharedPrefereces;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hfad.dzienniczekseniora.R;

/**
 * Class handle sharePreferences for reference values of glucose
 */
public class GlucoseSharedPreferences {
    private Context context;
    private android.content.SharedPreferences preferences;
    private static final String PREFERENCES_NAME = "glucoseReferenceValues";
    private static final String PREFERENCES_HIGHER_KEY = "higher";
    private static final String PREFERENCES_LOWER_KEY = "lower";

    public GlucoseSharedPreferences(Context context) {
        this.context=context;
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
    }

    /**
     * Method saves data in sharedPreferences
     * @param lowerData
     * @param higherData
     */
    public void saveData(float lowerData, float higherData) {
        android.content.SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putFloat(PREFERENCES_HIGHER_KEY, higherData);
        preferencesEditor.putFloat(PREFERENCES_LOWER_KEY, lowerData);
        preferencesEditor.commit();
    }

    /**
     *
     * @return
     */
    public float getHigherBoarder() {
        return preferences.getFloat(PREFERENCES_HIGHER_KEY, context.getResources().getInteger(R.integer.higherGlucoseBoarder));
    }

    /**
     *
     * @return
     */
    public float getLowerBoarder() {
        return preferences.getFloat(PREFERENCES_LOWER_KEY, context.getResources().getInteger(R.integer.lowerGlucoseBoarder));
    }

    /**
     * Method clears sharedPreferences data
     */
    public void removeGlucoseDataSP(){
        SharedPreferences.Editor preferencesEditor=preferences.edit();
        preferencesEditor.clear().commit();
    }
}
=======
package com.hfad.dzienniczekseniora.asharedPrefereces;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.hfad.dzienniczekseniora.R;

/**
 * Class handle sharePreferences for reference values of glucose
 */
public class GlucoseSharedPreferences {
    private Context context;
    private android.content.SharedPreferences preferences;
    private static final String PREFERENCES_NAME = "glucoseReferenceValues";
    private static final String PREFERENCES_HIGHER_KEY = "higher";
    private static final String PREFERENCES_LOWER_KEY = "lower";

    public GlucoseSharedPreferences(Context context) {
        this.context=context;
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Activity.MODE_PRIVATE);
    }

    /**
     * Method saves data in sharedPreferences
     * @param lowerData
     * @param higherData
     */
    public void saveData(float lowerData, float higherData) {
        android.content.SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putFloat(PREFERENCES_HIGHER_KEY, higherData);
        preferencesEditor.putFloat(PREFERENCES_LOWER_KEY, lowerData);
        preferencesEditor.commit();
    }

    /**
     * Method returns Higher Boarder of Glucose
     * @return
     */
    public float getHigherBoarder() {
        return preferences.getFloat(PREFERENCES_HIGHER_KEY, context.getResources().getInteger(R.integer.higherGlucoseBoarder));
    }

    /**
     * Method returns Lower Boarder of Glucose
     * @return
     */
    public float getLowerBoarder() {
        return preferences.getFloat(PREFERENCES_LOWER_KEY, context.getResources().getInteger(R.integer.lowerGlucoseBoarder));
    }

    /**
     * Method clears sharedPreferences data
     */
    public void removeGlucoseDataSP(){
        SharedPreferences.Editor preferencesEditor=preferences.edit();
        preferencesEditor.clear().commit();
    }
}
>>>>>>> master
