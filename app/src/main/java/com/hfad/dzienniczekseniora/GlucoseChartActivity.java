package com.hfad.dzienniczekseniora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.hfad.dzienniczekseniora.database.DbController;

import java.util.ArrayList;
import java.util.List;

public class GlucoseChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_chart);

        DbController db = new DbController(this);
        List list = db.getGlucose30Data();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<BarEntry> value = new ArrayList<>();

        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                date.add(object.get(1).toString());
                System.out.print("Data: " + object.get(1).toString() + "\n");
                value.add(new BarEntry((Float.parseFloat(object.get(3).toString())), i));
                System.out.print("Wartość: " + object.get(3).toString() + "\n");
            }

            BarDataSet dataset = new BarDataSet(value, "Cukier");
            BarChart chart = new BarChart(this);
            setContentView(chart);


            BarData data = new BarData(date, dataset);
            chart.setData(data);
        }
        catch(Exception e){
            Log.d("Error", e.toString());
        }
    }
}
