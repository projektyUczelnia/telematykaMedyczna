package com.hfad.dzienniczekseniora;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;

public class PressureActivity extends AppCompatActivity {
    private int mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");

        Button addTimeButton = findViewById(R.id.addTime5);
        final TextView timeTextView = findViewById(R.id.editText7);
        Button saveButton = findViewById(R.id.saveButton5);
        final TextView timeTextViewPressure1 = findViewById(R.id.editText8);
        final TextView timeTextViewPressure2 = findViewById(R.id.editText10);

        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(PressureActivity.this,
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
                Intent intentBackToMainActivity = new Intent(PressureActivity.this, MainActivity.class);
                startActivity(intentBackToMainActivity);
            }
        });
    }
}
