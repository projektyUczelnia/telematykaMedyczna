package com.hfad.dzienniczekseniora;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
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
import com.hfad.dzienniczekseniora.database.DbController;
import com.hfad.dzienniczekseniora.database.SQLiteExcel;

import java.io.File;
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
        permission();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        final String currentDate = calendar.get(Calendar.DAY_OF_MONTH) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar.YEAR);

        Button addNoteButton = findViewById(R.id.addNote);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //przejscie do nowego okna wyboru notatki do dodania do obecnego dnia
                Intent intentChoiceData = new Intent(MainActivity.this, ChoiceData.class);
                Calendar cal = Calendar.getInstance();
                int month = cal.get(Calendar.MONTH);
                intentChoiceData.putExtra("date", cal.get(Calendar.YEAR) + "-" + String.valueOf(month + 1) +
                        "-" + cal.get(Calendar.DAY_OF_MONTH));
                startActivity(intentChoiceData);
            }
        });

        Button resultButton = findViewById(R.id.result);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //przejscie do okna wyswietlania wynikow z 30 dni

                Intent intentResult = new Intent(MainActivity.this, ResultFrom30Days.class);
                Calendar cal = Calendar.getInstance();
                int month = cal.get(Calendar.MONTH);
                intentResult.putExtra("date", cal.get(Calendar.YEAR) + "-" + String.valueOf(month + 1) +
                        "-" + cal.get(Calendar.DAY_OF_MONTH));
                intentResult.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentResult);
            }
        });

        final CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, final int year, final int month, final int dayOfMonth) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

                String chooseDate = dayOfMonth + "-" + (month) + "-" + year;
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
                if (chooseDateDate.after(currentDateDate) || chooseDateDate.equals(currentDateDate)) {
                    alertDialogBuilder.setPositiveButton(R.string.addVisit, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentAddVisit = new Intent(MainActivity.this, AddVisit.class);
                            int month2 = month + 1;
                            intentAddVisit.putExtra("date", year + "-" + month2 + "-" + dayOfMonth);
                            intentAddVisit.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(intentAddVisit);
                        }
                    });
                }
                if (chooseDateDate.before(currentDateDate) || chooseDateDate.equals(currentDateDate)) {
                    alertDialogBuilder.setNegativeButton(R.string.viewNote, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intentChoiceData = new Intent(MainActivity.this, ChoiceData.class);
                            intentChoiceData.putExtra("showData", true);
                            int month2 = month + 1;
                            intentChoiceData.putExtra("date", year + "-" + month2 + "-" + dayOfMonth);
                            intentChoiceData.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
        db = new DbController(this);
        //TODO to ponizej wszystko mozna wywalić jezeli tylko nie jest nam potrzebny klawisz z usówaniem danych oraz w menu.xml usunać item o id:DeleteWeight
        switch (item.getItemId()) {
            case R.id.changeReferenceValuesButton:
                Intent intent = new Intent(this, ChangeReferenceValues.class);
                startActivity(intent);
                break;
            case R.id.VisitInFuture:
                Intent intentVisit = new Intent(this, VisitFutureActivity.class);
                startActivity(intentVisit);
                break;
            case R.id.DeleteWeight:
                db.getDeleteWeight();
                break;
            case R.id.DeleteGlucose:
                db.getDeleteGlucose();
                break;
            case R.id.DeleteOthers:
                db.getDeleteOtherData();
                break;
            case R.id.DeletePressure:
                db.getDeletePressure();
                break;
            case R.id.DeleteTemperature:
                db.getDeleteTemperature();
                break;
            case R.id.DeleteVisits:
                db.getDeleteVisits();
                break;
            case R.id.ExportDataToExcel:
                SQLiteExcel sqLiteExcel = new SQLiteExcel(getApplicationContext());
                sqLiteExcel.ifFileExistsAndCreate();
                sqLiteExcel.checkIfXslCreated();
                sendMail(sqLiteExcel);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void sendMail(SQLiteExcel sqLiteExcel) {
        File file = new File(sqLiteExcel.getXslFile());
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("application/message");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"janekpro996@gmail.com"});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "MySubject");
        Uri URI = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
        mailIntent.putExtra(Intent.EXTRA_STREAM, URI);
        startActivity(Intent.createChooser(mailIntent, "Send it out!"));

    }


    public Date convertToDate(String date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date convertedDate = new Date();
        try {
            convertedDate = dateFormat.parse(date);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return convertedDate;
    }

    public void permission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                1);
    }
}
