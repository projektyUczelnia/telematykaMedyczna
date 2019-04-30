package com.hfad.dzienniczekseniora;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Calendar;

public class TemperatureActivity extends AppCompatActivity {
    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature);
        Intent intent = getIntent();
        String date = intent.getStringExtra("date");

        Button addTimeButton = findViewById(R.id.addTime3);
        final TextView timeTextView = findViewById(R.id.editText4);
        Button saveButton = findViewById(R.id.saveButton3);

        NumberPicker numberPicker = findViewById(R.id.lower_value_picker);
        numberPicker.setMinValue(35);
        numberPicker.setMaxValue(40);
        NumberPicker numberPicker2 = findViewById(R.id.lower_value_picker2);
        numberPicker2.setMinValue(0);
        numberPicker2.setMaxValue(9);

        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(TemperatureActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(android.widget.TimePicker view, int hourOfDay, int minute) {
                                timeTextView.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //zapis danych do bazy danych

                //jesli sie uda to:
                Intent intentBackToMainActivity = new Intent(TemperatureActivity.this, MainActivity.class);
                startActivity(intentBackToMainActivity);
            }
        });
    }
}
