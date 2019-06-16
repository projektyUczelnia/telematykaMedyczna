package com.hfad.dzienniczekseniora.changeReferenceValues;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.hfad.dzienniczekseniora.R;

/**
 * class define all views in single eddit layout
 */
public class SingkeEdditLayoutClass {
    Button changeValueButton;
    Button resetValueButton;
    TextView lastValues;
    TextView sectionName;
    NumberPicker lowerLeftNrPick;
    NumberPicker lowerRightNrPick;
    NumberPicker higherLeftNrPick;
    NumberPicker higherRightNrPick;
    TextView separatorSign1;
    TextView separatorSign2;
    TextView alletr;

    public SingkeEdditLayoutClass(View view, int position) {
        this.resetValueButton=view.findViewById(R.id.reset_reference_values);
        this.changeValueButton = view.findViewById(R.id.changeValueButton);
        this.lastValues = view.findViewById(R.id.lastValueTextView);
        this.sectionName = view.findViewById(R.id.sectionName);
        this.lowerLeftNrPick = view.findViewById(R.id.lower_value_left_nr_picker);
        this.lowerRightNrPick = view.findViewById(R.id.lower_value_right_nr_picker);
        this.higherLeftNrPick = view.findViewById(R.id.higher_value_left_nr_picker);
        this.higherRightNrPick = view.findViewById(R.id.higher_value_right_nr_picker);
        this.separatorSign1 = view.findViewById(R.id.separator_sign1);
        this.separatorSign2 = view.findViewById(R.id.separator_sign2);
        this.alletr = view.findViewById(R.id.eddit_reference_values_allert);
    }

    /**
     * method sets max and min values of nr pickers
     * @param lowerLeft
     * @param lowerRight
     * @param higherLeft
     * @param higherRight
     * @param minL
     * @param maxL
     * @param minR
     * @param maxR
     */
    protected void setPickerValues(NumberPicker lowerLeft, NumberPicker lowerRight, NumberPicker higherLeft,
                                   NumberPicker higherRight, int minL, int maxL, int minR, int maxR) {
        lowerLeft.setMinValue(minL);
        lowerLeft.setMaxValue(maxL);
        lowerRight.setMinValue(minR);
        lowerRight.setMaxValue(maxR);
        higherLeft.setMinValue(minL);
        higherLeft.setMaxValue(maxL);
        higherRight.setMinValue(minR);
        higherRight.setMaxValue(maxR);
    }

    /**
     * Method sets separator sing
     * @param separatorSign1
     * @param separatorSign2
     * @param separator
     */
    protected void changeSeparatorSign(TextView separatorSign1, TextView separatorSign2, String separator) {

        separatorSign1.setText(separator);
        separatorSign2.setText(separator);

    }
}