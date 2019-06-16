package com.hfad.dzienniczekseniora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hfad.dzienniczekseniora.changeReferenceValues.Glucose;

//import com.github.mikephil.charting.charts.BarChart;

public class ResultFrom30Days extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_from30_days);

//        BarChart chart = new BarChart(this);
//        setContentView(chart);

        Button weight = findViewById(R.id.weightChart);
        Button temperature = findViewById(R.id.temperatureChart);
        Button glucose = findViewById(R.id.glucoseChart);
        Button pressure = findViewById(R.id.pressureChart);

        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWeight = new Intent(ResultFrom30Days.this, WeightChartActivity.class);
                startActivity(intentWeight);
            }
        });

        temperature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWeight = new Intent(ResultFrom30Days.this, TemperatureChartActivity.class);
                startActivity(intentWeight);
            }
        });

        glucose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWeight = new Intent(ResultFrom30Days.this, GlucoseChartActivity.class);
                startActivity(intentWeight);
            }
        });

        pressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWeight = new Intent(ResultFrom30Days.this, PressureChartActivity.class);
                startActivity(intentWeight);
            }
        });
    }
}
