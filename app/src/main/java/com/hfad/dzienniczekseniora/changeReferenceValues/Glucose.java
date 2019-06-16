package com.hfad.dzienniczekseniora.changeReferenceValues;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hfad.dzienniczekseniora.R;
import com.hfad.dzienniczekseniora.asharedPrefereces.GlucoseSharedPreferences;

/**
 * Class perform change reference value of glucose
 */
public class Glucose extends SingkeEdditLayoutClass {

    private GlucoseSharedPreferences glucoseSharedPreferences;

    public Glucose(View view, int position, Context context) {
        super(view, position);
        glucoseSharedPreferences = new GlucoseSharedPreferences(context);
        //TODO set correct values
        setPickerValues(lowerLeftNrPick, lowerRightNrPick, higherLeftNrPick, higherRightNrPick,
                50, 170, 0, 9);
        changeSeparatorSign(separatorSign1, separatorSign2, ".");
        changeValueButtonClick(changeValueButton);
        resetValueButtonClick(resetValueButton);
        sectionName.setText(R.string.glucose);
        setLastValues();
    }

    /**
     * Method define reset value button click
     * @param resetValueButton
     */
    private void resetValueButtonClick(Button resetValueButton) {
        resetValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                glucoseSharedPreferences.removeGlucoseDataSP();
                setLastValues();
            }
        });

    }

    /**
     * Method define change value button click
     * @param changeValueButton
     */
    protected void changeValueButtonClick(Button changeValueButton) {
        changeValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lowerValueString = +lowerLeftNrPick.getValue() + "." + lowerRightNrPick.getValue();
                String higherValueString = +higherLeftNrPick.getValue() + "." + higherRightNrPick.getValue();
                float lowerValue = Float.parseFloat(lowerValueString);
                float higherValue = Float.parseFloat(higherValueString);
                checkIfValuesAreCorrect(lowerValue, higherValue);
                Log.d("glucose", "clicked");
            }
        });
    }

    /**
     * Method checks if inpured data are correct
     * @param lowerValue
     * @param higherValue
     */
    private void checkIfValuesAreCorrect(float lowerValue, float higherValue) {
        if (lowerValue >= higherValue) {
            Log.d("glucose", "cus nie teges");
            alletr.setVisibility(View.VISIBLE);
        } else {
            glucoseSharedPreferences.saveData(lowerValue, higherValue);
            alletr.setVisibility(View.INVISIBLE);
            setLastValues();
        }
    }

    /**
     * Method sets last reference values as a text view in UI
     */
    private void setLastValues() {
        lastValues.setText(glucoseSharedPreferences.getHigherBoarder() + ">...>" +
                glucoseSharedPreferences.getLowerBoarder());
    }

}
