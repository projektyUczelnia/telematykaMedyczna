package com.hfad.dzienniczekseniora;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hfad.dzienniczekseniora.database.DbController;
import com.hfad.dzienniczekseniora.database.EnumTable;

import java.util.Calendar;

public class GlucoseActivity extends AppCompatActivity {
    private int mHour, mMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose);

        Intent intent = getIntent();
        final String date = intent.getStringExtra("date");

        Button addTimeButton = findViewById(R.id.addTime4);
        final TextView timeTextView = findViewById(R.id.editText5);
        final TextView TextViewGlucose = findViewById(R.id.editText6);
        Button saveButton = findViewById(R.id.saveButton4);

        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(GlucoseActivity.this,
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

                DbController db = new DbController(GlucoseActivity.this);
                db.insert_data(EnumTable.GLUCOSE.returnTableConstValues(), date,
                        timeTextView.getText().toString(), TextViewGlucose.getText().toString());

                //jesli sie uda to:
                Intent intentBackToMainActivity = new Intent(GlucoseActivity.this, ChoiceData.class);
                Calendar cal = Calendar.getInstance();
                intentBackToMainActivity.putExtra("date", cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) +
                        "-" + cal.get(Calendar.DAY_OF_MONTH));
                intentBackToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentBackToMainActivity);
            }
        });
    }
}