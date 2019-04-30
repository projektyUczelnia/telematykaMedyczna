package com.hfad.dzienniczekseniora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChoiceData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_data);

        Intent intent = getIntent();
        Boolean showData = intent.getBooleanExtra("showData", false);

        Button weightButton = findViewById(R.id.weightButton);
        Button temperatureButton = findViewById(R.id.temperatureButton);
        Button glucoseButton = findViewById(R.id.glucoseButton);
        Button pressureButton = findViewById(R.id.pressureButton);
        Button otherButton = findViewById(R.id.otherButton);
        Button visitButton = findViewById(R.id.visitButton);

        TextView weightView = findViewById(R.id.weightView);
        scrollingMethod(weightView);
        TextView temperatureView = findViewById(R.id.temperatureView);
        scrollingMethod(temperatureView);
        TextView glucoseView = findViewById(R.id.glucoseView);
        scrollingMethod(glucoseView);
        TextView pressureView = findViewById(R.id.pressureView);
        scrollingMethod(pressureView);
        TextView visitView = findViewById(R.id.visitView);
        scrollingMethod(visitView);
        TextView otherView = findViewById(R.id.otherView);
        scrollingMethod(otherView);

        if(showData) {
            weightButton.setEnabled(false);
            temperatureButton.setEnabled(false);
            glucoseButton.setEnabled(false);
            pressureButton.setEnabled(false);
            otherButton.setEnabled(false);
            visitButton.setEnabled(false);
        }
        else {
            weightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddWeight = new Intent(ChoiceData.this, WeightActivity.class);
                    Calendar cal = Calendar.getInstance();
                    intentAddWeight.putExtra("date", cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    startActivity(intentAddWeight);
                }
            });

            temperatureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddTemperature = new Intent(ChoiceData.this, TemperatureActivity.class);
                    Calendar cal = Calendar.getInstance();
                    intentAddTemperature.putExtra("date", cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    startActivity(intentAddTemperature);
                }
            });

            glucoseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddGlucose = new Intent(ChoiceData.this, GlucoseActivity.class);
                    Calendar cal = Calendar.getInstance();
                    intentAddGlucose.putExtra("date", cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    startActivity(intentAddGlucose);
                }
            });

            pressureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddPressure = new Intent(ChoiceData.this, PressureActivity.class);
                    Calendar cal = Calendar.getInstance();
                    intentAddPressure.putExtra("date", cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    startActivity(intentAddPressure);
                }
            });

            otherButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddOther = new Intent(ChoiceData.this, OtherActivity.class);
                    Calendar cal = Calendar.getInstance();
                    intentAddOther.putExtra("date", cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    startActivity(intentAddOther);
                }
            });

            visitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddVisit = new Intent(ChoiceData.this, AddVisit.class);
                    Calendar cal = Calendar.getInstance();
                    intentAddVisit.putExtra("date", cal.get(Calendar.YEAR) + "-" + cal.get(Calendar.MONTH) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    startActivity(intentAddVisit);
                }
            });
        }
    }

    private void scrollingMethod(TextView textView){
        textView.setMovementMethod(new ScrollingMovementMethod());
    }
}
