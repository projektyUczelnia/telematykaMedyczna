package com.hfad.dzienniczekseniora;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hfad.dzienniczekseniora.database.DbController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VisitFutureActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_future);

        TextView view = findViewById(R.id.visitFutureView);

        DbController db = new DbController(this);
        List list = db.getAllVisitsFuture();
        String nowDate = Calendar.YEAR +"-"+Calendar.MONTH+1+"-"+Calendar.DAY_OF_MONTH ;
        MainActivity mainActivity = new MainActivity();
        Date date = mainActivity.convertToDate(nowDate);
        try {
            for (int i = 0; i < list.size(); i++) {
                List object = (List) list.get(i);

                Date dateFromList = mainActivity.convertToDate(object.get(1).toString());

                if(dateFromList.after(date)) {
                    view.append("\n");
                    view.append("Data: " + object.get(1).toString() + "\n");
                    view.append("Godzina: " + object.get(2).toString() + "\n");
                    view.append("Dodatkowe informacje: " + object.get(3).toString() + "\n");
                    view.append("________________\n");
                }
            }
        }
        catch(Exception e){
            Log.d("Error", "Lista pusta");
        }
    }
}
