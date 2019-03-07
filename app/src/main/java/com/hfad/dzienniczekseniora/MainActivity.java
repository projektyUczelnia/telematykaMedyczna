package com.hfad.dzienniczekseniora;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addNote = findViewById(R.id.addNote);

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //przejscie do nowego okna wyboru notatki do dodania do obecnego dnia
                Intent intentChoiceData = new Intent(MainActivity.this, ChoiceData.class);
                startActivity(intentChoiceData);
            }
        });


        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //przejscie do okna wyswietlenia notatek z wybranego dnia

                Intent intentShowData = new Intent(MainActivity.this, ShowData.class);
                startActivity(intentShowData);
            }
        });
    }
}
