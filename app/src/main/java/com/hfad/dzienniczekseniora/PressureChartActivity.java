package com.hfad.dzienniczekseniora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.hfad.dzienniczekseniora.database.DbController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class PressureChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pressure_chart);

        DbController db = new DbController(this);
        List list = db.getPressure30Data();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<BarEntry> value = new ArrayList<>();

        String nowDate = Calendar.YEAR +"-"+Calendar.MONTH+"-"+Calendar.DAY_OF_MONTH ;
        MainActivity mainActivity = new MainActivity();
        Date dateNow = mainActivity.convertToDate(nowDate);

        int j=0;
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                Date dateFromList = mainActivity.convertToDate(object.get(1).toString());

                if(dateFromList.after(dateNow)) {
                    date.add(object.get(1).toString());
                    date.add(object.get(1).toString());
                    System.out.print("Data: " + object.get(1).toString() + "\n");
                    String valuePressure = object.get(3).toString();
                    String[] pressure = valuePressure.split("/");
                    value.add(new BarEntry((Float.parseFloat(pressure[0])), j));
                    value.add(new BarEntry((Float.parseFloat(pressure[1])), ++j));
                    System.out.print("Wartość: " + object.get(3).toString() + "\n");
                    j++;
                }
            }

            BarDataSet dataset = new BarDataSet(value, "Ciśnienie skurczowe");
           // dataset.setColors(ColorTemplate.VORDIPLOM_COLORS);
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
