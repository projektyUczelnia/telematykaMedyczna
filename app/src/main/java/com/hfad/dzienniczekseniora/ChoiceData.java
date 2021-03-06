package com.hfad.dzienniczekseniora;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.method.ScrollingMovementMethod;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hfad.dzienniczekseniora.asharedPrefereces.BMISharedPreferences;
import com.hfad.dzienniczekseniora.asharedPrefereces.BloodPressureSharePreferences;
import com.hfad.dzienniczekseniora.asharedPrefereces.GlucoseSharedPreferences;
import com.hfad.dzienniczekseniora.database.DbController;

import java.util.Calendar;
import java.util.List;

public class ChoiceData extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice_data);

        Intent intent = getIntent();
        Boolean showData = intent.getBooleanExtra("showData", false);
        String date = intent.getStringExtra("date");

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

        showDataInWindowWeight(date, weightView);
        showDataInWindowTemperature(date, temperatureView);
        showDataInWindowGlucose(date, glucoseView);
        showDataInWindowPressure(date, pressureView);
        showDataInWindowVisit(date, visitView);
        showDataInWindowOther(date, otherView);

        if (showData) {
            weightButton.setEnabled(false);
            temperatureButton.setEnabled(false);
            glucoseButton.setEnabled(false);
            pressureButton.setEnabled(false);
            otherButton.setEnabled(false);
            visitButton.setEnabled(false);

        } else {
            weightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddWeight = new Intent(ChoiceData.this, WeightActivity.class);
                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH);
                    intentAddWeight.putExtra("date", cal.get(Calendar.YEAR) + "-" + String.valueOf(month + 1) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    intentAddWeight.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentAddWeight);
                }
            });

            temperatureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddTemperature = new Intent(ChoiceData.this, TemperatureActivity.class);
                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH);
                    intentAddTemperature.putExtra("date", cal.get(Calendar.YEAR) + "-" + String.valueOf(month + 1) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    intentAddTemperature.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentAddTemperature);
                }
            });

            glucoseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddGlucose = new Intent(ChoiceData.this, GlucoseActivity.class);
                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH);
                    intentAddGlucose.putExtra("date", cal.get(Calendar.YEAR) + "-" + String.valueOf(month + 1) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    intentAddGlucose.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentAddGlucose);
                }
            });

            pressureButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddPressure = new Intent(ChoiceData.this, PressureActivity.class);
                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH);
                    intentAddPressure.putExtra("date", cal.get(Calendar.YEAR) + "-" + String.valueOf(month + 1) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    intentAddPressure.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentAddPressure);
                }
            });

            otherButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddOther = new Intent(ChoiceData.this, OtherActivity.class);
                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH);
                    intentAddOther.putExtra("date", cal.get(Calendar.YEAR) + "-" + String.valueOf(month + 1) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    intentAddOther.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentAddOther);
                }
            });

            visitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentAddVisit = new Intent(ChoiceData.this, AddVisit.class);
                    Calendar cal = Calendar.getInstance();
                    int month = cal.get(Calendar.MONTH);
                    intentAddVisit.putExtra("date", cal.get(Calendar.YEAR) + "-" + String.valueOf(month + 1) +
                            "-" + cal.get(Calendar.DAY_OF_MONTH));
                    intentAddVisit.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intentAddVisit);
                }
            });
        }
    }

    private void scrollingMethod(TextView textView) {
        textView.setMovementMethod(new ScrollingMovementMethod());
    }

    private void showDataInWindowWeight(String date, TextView view) {
        DbController db = new DbController(this);
        List list = db.getWeightData(date);
        BMISharedPreferences bmiSharedPreferences = new BMISharedPreferences(ChoiceData.this);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                builder.append("Data: " + object.get(1).toString() + "\n");
                builder.append("Godzina: " + object.get(2).toString() + "\n");
                builder.append("Wartość: " + object.get(3).toString() + "\n");
                double weight = Double.parseDouble(object.get(3).toString());
                if(bmiSharedPreferences.getHeight() != 0) {
                    double bmi = weight / ((bmiSharedPreferences.getHeight()/100) * (bmiSharedPreferences.getHeight()/100));
                    SpannableString bmiColor = new SpannableString(String.valueOf(bmi));
                    if (bmi < 18.5 || bmi >= 25) {
                        bmiColor.setSpan(new ForegroundColorSpan(Color.RED), 0, bmiColor.length(), 0);
                    } else {
                        bmiColor.setSpan(new ForegroundColorSpan(Color.GREEN), 0, bmiColor.length(), 0);
                    }
                    builder.append("BMI: ").append(bmiColor).append("\n");
                }
                builder.append("________________\n");
            }
            view.setText(builder, TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            Log.d("Error", "Lista pusta");
        }
    }

    private void showDataInWindowTemperature(String date, TextView view) {
        DbController db = new DbController(this);
        List list = db.getTempData(date);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                builder.append("Data: ").append(object.get(1).toString()).append("\n");
                builder.append("Godzina: ").append(object.get(2).toString()).append("\n");
                double temperature = Double.parseDouble(object.get(3).toString());
                SpannableString temperatureColor = new SpannableString(String.valueOf(temperature));
                if (temperature > 36.8 || temperature < 36.5) {
                    temperatureColor.setSpan(new ForegroundColorSpan(Color.RED), 0, temperatureColor.length(), 0);
                } else {
                    temperatureColor.setSpan(new ForegroundColorSpan(Color.GREEN), 0, temperatureColor.length(), 0);
                }
                builder.append("Wartość: ").append(temperatureColor).append("\n");
                builder.append("________________\n");
            }
            view.setText(builder, TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            Log.d("Error", "Lista pusta");
        }
    }

    private void showDataInWindowGlucose(String date, TextView view) {
        DbController db = new DbController(this);
        List list = db.getGlucoseData(date);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        GlucoseSharedPreferences glucoseSharedPreferences = new GlucoseSharedPreferences(ChoiceData.this);
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                builder.append("Data: ").append(object.get(1).toString()).append("\n");
                builder.append("Godzina: ").append(object.get(2).toString()).append("\n");
                double glucose = Double.parseDouble(object.get(3).toString());
                SpannableString glucoseColor = new SpannableString(String.valueOf(glucose));
                if (glucose > glucoseSharedPreferences.getHigherBoarder() || glucose < glucoseSharedPreferences.getLowerBoarder()) {
                    glucoseColor.setSpan(new ForegroundColorSpan(Color.RED), 0, glucoseColor.length(), 0);
                } else {
                    glucoseColor.setSpan(new ForegroundColorSpan(Color.GREEN), 0, glucoseColor.length(), 0);
                }
                builder.append("Wartość: ").append(glucoseColor).append("\n");
                builder.append("________________\n");
            }
            view.setText(builder, TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            Log.d("Error", "Lista pusta");
        }
    }

    private void showDataInWindowPressure(String date, TextView view) {
        DbController db = new DbController(this);
        List list = db.getPressureData(date);
        SpannableStringBuilder builder = new SpannableStringBuilder();
        BloodPressureSharePreferences bloodPressureSharePreferences = new BloodPressureSharePreferences(ChoiceData.this);
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                builder.append("Data: ").append(object.get(1).toString()).append("\n");
                builder.append("Godzina: ").append(object.get(2).toString()).append("\n");
                String pressure = object.get(3).toString();
                String[] pressureList = pressure.split("/");
                Double systolic = Double.parseDouble(pressureList[0]);
                Double diastolic = Double.parseDouble(pressureList[1]);

                SpannableString pressureColor= new SpannableString(String.valueOf(pressure));
                if(systolic>bloodPressureSharePreferences.getHigherSystolicPressure() ||
                        systolic<bloodPressureSharePreferences.getLowerSystolicPressure() ||
                        diastolic>bloodPressureSharePreferences.getHigherDiastolicPressure()||
                       diastolic<bloodPressureSharePreferences.getLowerDiastolicPressure()
                        || (systolic-diastolic)<40){
                    pressureColor.setSpan(new ForegroundColorSpan(Color.RED), 0, pressureColor.length(), 0);
                }
                else{
                    pressureColor.setSpan(new ForegroundColorSpan(Color.GREEN), 0, pressureColor.length(), 0);
                }
                Log.d("hsp", String.valueOf(bloodPressureSharePreferences.getHigherSystolicPressure()));
                Log.d("lsp", String.valueOf(bloodPressureSharePreferences.getLowerSystolicPressure()));
                Log.d("hdp", String.valueOf(bloodPressureSharePreferences.getHigherDiastolicPressure()));
                Log.d("lsp", String.valueOf(bloodPressureSharePreferences.getLowerDiastolicPressure()));
                builder.append("Wartość: ").append(pressureColor).append("\n");
                builder.append("________________\n");
            }
            view.setText(builder, TextView.BufferType.SPANNABLE);
        } catch (Exception e) {
            Log.d("Error", "Lista pusta");
        }
    }

    private void showDataInWindowOther(String date, TextView view) {
        DbController db = new DbController(this);
        List list = db.getOtherData(date);
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                view.append("Data: " + object.get(1).toString() + "\n");
                view.append("Godzina: " + object.get(2).toString() + "\n");
                view.append("Wartość: " + object.get(3).toString() + "\n");
                view.append("________________\n");
            }
        } catch (Exception e) {
            Log.d("Error", "Lista pusta");
        }
    }

    private void showDataInWindowVisit(String date, TextView view) {
        DbController db = new DbController(this);
        List list = db.getVisitData(date);
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                view.append("Data: " + object.get(1).toString() + "\n");
                view.append("Godzina: " + object.get(2).toString() + "\n");
                view.append("Wartość: " + object.get(3).toString() + "\n");
                view.append("________________\n");
            }
        } catch (Exception e) {
            Log.d("Error", "Lista pusta");
        }
    }

}
