package com.hfad.dzienniczekseniora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.LimitLine;
import com.hfad.dzienniczekseniora.database.DbController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class GlucoseChartActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glucose_chart);

        DbController db = new DbController(this);
        List list = db.getGlucose30Data();
        ArrayList<String> date = new ArrayList<String>();
        ArrayList<BarEntry> value = new ArrayList<>();
        String nowDate = Calendar.YEAR +"-"+Calendar.MONTH+"-"+Calendar.DAY_OF_MONTH ;
        MainActivity mainActivity = new MainActivity();
        Date dateNow = mainActivity.convertToDate(nowDate);

        int average = 0;

        int j=0;
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                Date dateFromList = mainActivity.convertToDate(object.get(1).toString());

                if(dateFromList.after(dateNow)) {
                    date.add(object.get(1).toString()+" "+object.get(2).toString());
                    System.out.print("Data: " + object.get(1).toString() + "\n");
                    value.add(new BarEntry((Float.parseFloat(object.get(3).toString())), j));
                    average += Integer.parseInt(object.get(3).toString());
                    System.out.print("Wartość: " + object.get(3).toString() + "\n");
                    j++;
                }
            }

            average= average/j;

            BarDataSet dataset = new BarDataSet(value, "Cukier");
            BarChart chart = new BarChart(this);
            chart.setDescription("# Poziom cukru z miesiaca");
            dataset.setColors(ColorTemplate.LIBERTY_COLORS);
            setContentView(chart);


            BarData data = new BarData(date, dataset);
            LimitLine line = new LimitLine(average);
            data.addLimitLine(line);
            chart.setData(data);
        }
        catch(Exception e){
            Log.d("Error", e.toString());
        }
    }
}
