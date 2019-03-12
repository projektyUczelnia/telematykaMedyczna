package com.hfad.dzienniczekseniora;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addNoteButton = findViewById(R.id.addNote);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //przejscie do nowego okna wyboru notatki do dodania do obecnego dnia
                Intent intentChoiceData = new Intent(MainActivity.this, ChoiceData.class);
                startActivity(intentChoiceData);
            }
        });

        Button resultButton = findViewById(R.id.result);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //przejscie do okna wyswietlania wynikow z 30 dni

                Intent intentResult = new Intent(MainActivity.this, ResultFrom30Days.class);
                startActivity(intentResult);
            }
        });

        final CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, final int year, final int month, final int dayOfMonth) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("WYBIERZ: ");

                alertDialogBuilder.setPositiveButton(R.string.addVisit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //przejscie do okna dodania wizyty
                        Intent intentAddVisit = new Intent(MainActivity.this, AddVisit.class);
                        intentAddVisit.putExtra("date", year + "-" + month + "-" + dayOfMonth);
                        startActivity(intentAddVisit);
                    }
                });

                alertDialogBuilder.setNegativeButton(R.string.viewNote, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //przejscie do okna wyswietlenia notatek z wybranego dnia trzeba zablokowac tam buttony

                        Intent intentChoiceData = new Intent(MainActivity.this, ChoiceData.class);
                        startActivity(intentChoiceData);
                    }
                });

                alertDialogBuilder.show();
            }
        });
    }

    //dodaje button do actionBaru
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //megoda umożliwiająca wykonanie akcji po naciśnięciu buttona w action barze
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.changeReferenceValuesButton) {
           Intent intent = new Intent(this,ChangeReferenceValues.class);
           startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

}
