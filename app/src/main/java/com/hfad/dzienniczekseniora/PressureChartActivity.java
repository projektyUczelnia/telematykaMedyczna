package com.hfad.dzienniczekseniora;

import android.graphics.Color;
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

public class PressureChartActivity extends BaseActivity {

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

        int average1 = 0;
        int average2 = 0;
        int j=0;
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);
                Date dateFromList = mainActivity.convertToDate(object.get(1).toString());

                if(dateFromList.after(dateNow)) {
                    date.add(object.get(1).toString()+" "+object.get(2).toString());
                    date.add("");
                    System.out.print("Data: " + object.get(1).toString() + "\n");
                    String valuePressure = object.get(3).toString();
                    String[] pressure = valuePressure.split("/");
                    value.add(new BarEntry((Float.parseFloat(pressure[0])), j));
                    average1 += Integer.parseInt(pressure[0]);
                    value.add(new BarEntry((Float.parseFloat(pressure[1])), ++j));
                    average2 += Integer.parseInt(pressure[1]);
                    System.out.print("Wartość: " + object.get(3).toString() + "\n");
                    j++;
                }
            }

            average1= average1/(j/2);
            average2= average2/(j/2);

            BarDataSet dataset = new BarDataSet(value, "skurczowe/ rozkurczowe");
            final int[] colors =new int[]{Color.rgb(255, 0, 0), Color.rgb(13, 0, 237)};
            ColorTemplate colorTemplate = new ColorTemplate();
            dataset.setColors(ColorTemplate.createColors(colors));
            BarChart chart = new BarChart(this);
            chart.setDescription("# Ciśnienie z miesiaca");
            setContentView(chart);

            BarData data = new BarData(date, dataset);
            LimitLine line1 = new LimitLine(average1);
            data.addLimitLine(line1);
            LimitLine line2 = new LimitLine(average2);
            data.addLimitLine(line2);
            chart.setData(data);

        }
        catch(Exception e){
            Log.d("Error", e.toString());
        }
    }
}
