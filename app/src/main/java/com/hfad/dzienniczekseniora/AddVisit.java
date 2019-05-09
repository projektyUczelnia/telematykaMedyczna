package com.hfad.dzienniczekseniora;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hfad.dzienniczekseniora.database.DbController;
import com.hfad.dzienniczekseniora.database.EnumTable;

import java.util.Calendar;

public class AddVisit extends AppCompatActivity {
    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_visit);

        Intent intent = getIntent();
        final String date = intent.getStringExtra("date");

        Button addTimeButton = findViewById(R.id.addTime);
        Button saveButton = findViewById(R.id.saveButton);
        final TextView timeTextView = findViewById(R.id.timeTextView);
        final TextView dateTextView = findViewById(R.id.dataView);
        final TextView TextViewInformations = findViewById(R.id.editText3);

        dateTextView.setText(date);

        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(AddVisit.this,
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

                DbController db = new DbController(AddVisit.this);
                db.insert_data(EnumTable.VISIT.returnTableConstValues(), date,
                        timeTextView.getText().toString(), TextViewInformations.getText().toString());

                //jesli sie uda to:
                Intent intentBackToMainActivity = new Intent(AddVisit.this, ChoiceData.class);
                intentBackToMainActivity.putExtra("date", date);
                Calendar cal = Calendar.getInstance();
                int month = cal.get(Calendar.MONTH);
                String currentDate = cal.get(Calendar.YEAR) + "-" + String.valueOf(month+1) +
                        "-" + cal.get(Calendar.DAY_OF_MONTH);
                if (!date.equals(currentDate)) {
                    intentBackToMainActivity.putExtra("showData", true);
                }
                intentBackToMainActivity.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY );
                startActivity(intentBackToMainActivity);
            }
        });
    }
}
