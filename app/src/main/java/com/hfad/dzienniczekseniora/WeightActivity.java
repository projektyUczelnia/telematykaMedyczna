package com.hfad.dzienniczekseniora;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hfad.dzienniczekseniora.database.DbController;
import com.hfad.dzienniczekseniora.database.EnumTable;

import java.util.Calendar;

public class WeightActivity extends BaseActivity {
    private int mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);

        Intent intent = getIntent();
        final String date = intent.getStringExtra("date");

        Button addTimeButton = findViewById(R.id.addTime2);
        final TextView timeTextView = findViewById(R.id.editText2);
        final TextView TextViewWeight = findViewById(R.id.editText);
        Button saveButton = findViewById(R.id.saveButton2);

        addTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(WeightActivity.this,
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

                    DbController db = new DbController(WeightActivity.this);
                    db.insert_data(EnumTable.WEIGHT.returnTableConstValues(), date,
                            timeTextView.getText().toString(), TextViewWeight.getText().toString());

                    //jesli sie uda to:
                    Intent intentBackToMainActivity = new Intent(WeightActivity.this, ChoiceData.class);
                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH);
                    intentBackToMainActivity.putExtra("date", cal.get(Calendar.YEAR) + "-" + String.valueOf(month+1) +
                        "-" + cal.get(Calendar.DAY_OF_MONTH));
                    intentBackToMainActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentBackToMainActivity);

            }
        });
    }
}
