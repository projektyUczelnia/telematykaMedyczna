package com.hfad.dzienniczekseniora.changeReferenceValues;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.hfad.dzienniczekseniora.R;
import com.hfad.dzienniczekseniora.asharedPrefereces.BloodPressureSharePreferences;

/**
 * Class perform change reference value of blood pressure
 */
public class BloodPressure extends SingkeEdditLayoutClass {
    private BloodPressureSharePreferences bloodPressureSharePreferences;

    public BloodPressure(View view, int position, Context context) {
        super(view, position);
        bloodPressureSharePreferences = new BloodPressureSharePreferences(context);
        //TODO set correct Values
        setPickerValues(lowerLeftNrPick, lowerRightNrPick, higherLeftNrPick, higherRightNrPick,
                50, 200, 50, 200);
        changeSeparatorSign(separatorSign1, separatorSign2, "/");
        changeValueButtonClick(changeValueButton);
        resetValueButtonClick(resetValueButton);
        sectionName.setText(R.string.pressure);
        setLastValues();
    }

    /**
     * Method define reset value button click
     *
     * @param resetValueButton
     */
    private void resetValueButtonClick(Button resetValueButton) {
        resetValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodPressureSharePreferences.removeBloodPreasureDataSP();
                setLastValues();
            }
        });
    }

    /**
     * Method define change value button click
     *
     * @param changeValueButton
     */
    private void changeValueButtonClick(Button changeValueButton) {
        changeValueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("blood", "clicked");
                int lowerSystolicPressure = lowerLeftNrPick.getValue();
                int lowerDiastolicPressure = lowerRightNrPick.getValue();
                int higherSystolicPressure = higherLeftNrPick.getValue();
                int higherDiastolicPressure = higherRightNrPick.getValue();
                checkIfValuesAreCorrect(lowerSystolicPressure, lowerDiastolicPressure,
                        higherSystolicPressure, higherDiastolicPressure);
                setLastValues();
            }
        });
    }

    /**
     * Method checks if inpured data are correct
     *
     * @param lowerSystolicPressure
     * @param lowerDiastolicPressure
     * @param higherSystolicPressure
     * @param higherDiastolicPressure
     */
    private void checkIfValuesAreCorrect(int lowerSystolicPressure, int lowerDiastolicPressure,
                                         int higherSystolicPressure, int higherDiastolicPressure) {
        if (lowerDiastolicPressure >= higherDiastolicPressure ||
                lowerSystolicPressure >= higherSystolicPressure) {
            alletr.setVisibility(View.VISIBLE);
            Log.d("glucose", "cus nie teges");
        } else {
            bloodPressureSharePreferences.saveData(lowerSystolicPressure, lowerDiastolicPressure,
                    higherSystolicPressure, higherDiastolicPressure);
            alletr.setVisibility(View.INVISIBLE);
            setLastValues();
        }
    }

    /**
     * Method sets last reference values as a text view in UI
     */
    private void setLastValues() {
        String lastBloodPreasureReferenceValues = bloodPressureSharePreferences.getHigherSystolicPressure() + "/" +
                bloodPressureSharePreferences.getHigherDiastolicPressure() + ">...>" +
                bloodPressureSharePreferences.getLowerSystolicPressure() + "/" +
                bloodPressureSharePreferences.getLowerDiastolicPressure();
        lastValues.setText(lastBloodPreasureReferenceValues);

    }


}
