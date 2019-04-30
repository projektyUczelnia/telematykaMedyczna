package com.hfad.dzienniczekseniora;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;

import com.hfad.dzienniczekseniora.database.DbHelper;
import com.hfad.dzienniczekseniora.database.EnumTable;
import com.hfad.dzienniczekseniora.database.DbController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        final String currentDate = calendar.get(Calendar.DAY_OF_MONTH)+"-"+calendar.get(Calendar.MONTH)+"-"+calendar.get(Calendar.YEAR);
        /*
        PORADNIK DLA MARTY!!!!!!!!!!!!!!!!

        dane przechowywane w stringu!!!!!!!!!!!!!!!

         JAK DODAWAC DANE DO BD:

            db = new DbController(this);

            DANE DO TABELI TEMPERATURY:
            db.insert_data(EnumTable.TEMPERATURE.returnTableConstValues(),"data1","czas","war");

            DANE DO TABELI POZIOMU CUKRU
            db.insert_data(EnumTable.GLUCOSE.returnTableConstValues(),"data1","czas","war");

            RESZTA ANALOGICZNIE
            JAK DANE SIE DODADZA W LOGACH WYSWIETLI SIE KOM "Data inserted" JAK NIE "Data not inserted"


         JAK POBRAC DANE Z BAZY DANYCH:
             db = new DbController(this);

             PONIZEJ LIŚCIE LOL ZOSTANIE PRZYPISANA LISTA LIST
             PARAMETR DATA2 MOWI Z JAKIEGO DNIA CHCESZ DANE I ŁADUJE JE DO LISTY LOL
             List lol = db.getGlucoseData("data2");

             PONIZEJ DO COL1 ZOSTANIE PRZYPISANA LISTA Z POPRZENIEJ LISTY XD
             List col1 = (List) lol.get(0);

             A NIŻEJ WYKORZYSTANIE DANYCH Z TEJ LISTY DO WYPISANIA DANYCH Z JEDNEGO WIERSZA BAZY DANYCH
             Log.d("lol", (String) col1.get(0) + "\n" + col1.get(1) + "\n" + col1.get(2) + "\n" + col1.get(3));

             JAK DANYCH NIE BEDZIE ZOSTANIE ZWRÓCONY NULL
             INDEKSOWANIE LIST WEWNĘTRZNYCH (w przykładzie col1):
             0-ID
             1-DATA
             2-CZAS
             3-WARTOŚĆ

          JAK LATWO SPRAWDZIC ILOŚĆ WPROWADZONYCH DANYCH?:
             db = new DbController(this);
             List lol = db.getGlucoseData("data2")
             int count = lol.size();
             COUT TO BEDZIE LICZBA WIERSZY Z BD

          CZEMU CHUJOWA LISTA W LIŚCIE??
             PONIEWAŻCHCIAŁEM ZEBYS JEDNA KOMENDA ZACIAGAŁA WSZYSTKIE DANE Z JEDNEGO DNIA
             A ZE ZAZWYCZAJ JEST PO KILKA WIERSZY Z KILKOMA WARTOŚCIMI UWAZAM ZE TO JEST
             NAJPROSTSZA METODA XD

             NIZEJ MOZESZ ZOBACZYC ZASTOSOWANIE W PRAKTYCE:

      */
        //przykład
        db = new DbController(this);

        db.insert_data(EnumTable.WEIGHT.returnTableConstValues(),"data","czas","war");
        if (db.getWeightData("data")!=null) {
            List lol = db.getWeightData("data");
            int count = lol.size();
            Log.d("lol", String.valueOf(count));
            List col1 = (List) lol.get(0);
            Log.d("lol", (String) col1.get(0) + "\n" + col1.get(1) + "\n" + col1.get(2) + "\n" + col1.get(3));
        }else  Log.d("lol", "nie ma danych");

        //koniec przykłaadu


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
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String chooseDate = dayOfMonth+"-"+month+"-"+year;
                Date currentDateDate = null;
                Date chooseDateDate = null;
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("WYBIERZ: ");

                try {
                    currentDateDate = simpleDateFormat.parse(currentDate);
                    chooseDateDate = simpleDateFormat.parse(chooseDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(chooseDateDate.after(currentDateDate) || chooseDateDate.equals(currentDateDate)) {
                    alertDialogBuilder.setPositiveButton(R.string.addVisit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentAddVisit = new Intent(MainActivity.this, AddVisit.class);
                            intentAddVisit.putExtra("date", year + "-" + month + "-" + dayOfMonth);
                            startActivity(intentAddVisit);
                        }
                    });
                }
                if(chooseDateDate.before(currentDateDate) || chooseDateDate.equals(currentDateDate)) {
                    alertDialogBuilder.setNegativeButton(R.string.viewNote, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentChoiceData = new Intent(MainActivity.this, ChoiceData.class);
                            intentChoiceData.putExtra("showData", true);
                            startActivity(intentChoiceData);
                        }
                    });
                }
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
