package com.hfad.dzienniczekseniora;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.hfad.dzienniczekseniora.database.DbController;
import com.hfad.dzienniczekseniora.database.DbHelper;
import com.hfad.dzienniczekseniora.database.SQLiteExcel;

import java.io.File;

/**
 * Activity makes a frame for all acfivities
 */
public class BaseActivity extends AppCompatActivity {
    DeleteDataInterface deleteDataInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Methods sets functionality of menu buttons
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final DbHelper db = new DbController(this);


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
            case R.id.ExportDataToExcel:
                SQLiteExcel sqLiteExcel = new SQLiteExcel(getApplicationContext());
                sqLiteExcel.ifFileExistsAndCreate();
                sqLiteExcel.checkIfXslCreated();
                sendMail(sqLiteExcel);
                break;
            case R.id.DeleteWeight:
                deleteDataInterface = new DeleteDataInterface() {
                    @Override
                    public void deleteData() {
                        db.getDeleteWeight();
                    }
                };
                allert("o wadze?");
                break;
            case R.id.DeleteTemperature:
                deleteDataInterface = new DeleteDataInterface() {
                    @Override
                    public void deleteData() {
                        db.getDeleteTemperature();
                    }
                };
                allert("o temperaturze?");
                break;
            case R.id.DeleteGlucose:
                deleteDataInterface = new DeleteDataInterface() {
                    @Override
                    public void deleteData() {
                        db.getDeleteGlucose();
                    }
                };
                allert("o poziomie cukru?");
                break;
            case R.id.DeletePressure:
                deleteDataInterface = new DeleteDataInterface() {
                    @Override
                    public void deleteData() {
                        db.getDeletePressure();
                    }
                };
                allert("o ciśnieniu?");
                break;
            case R.id.DeleteVisits:
                deleteDataInterface = new DeleteDataInterface() {
                    @Override
                    public void deleteData() {
                        db.getDeleteVisits();
                    }
                };
                allert("o wizytach?");
                break;
            case R.id.DeleteOthers:
                deleteDataInterface = new DeleteDataInterface() {
                    @Override
                    public void deleteData() {
                        db.getDeleteOtherData();
                    }
                };
                allert("inne?");
                break;
            case R.id.BmiValues:
                Intent intent1 = new Intent(this, InformationPerson.class);
                startActivity(intent1);
                break;
            case R.id.MainWindow:
                Intent intent2 = new Intent(this, MainActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
        
    }

    /**
     * Method shows allert when user want to delete data
     * @param dataName
     */
    public void allert(String dataName) {
        AlertDialog.Builder allert = new AlertDialog.Builder(this);
        allert.setMessage("Czy chcesz usunąć dane " + dataName);
        allert.setCancelable(true);
        allert.setPositiveButton("tak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteDataInterface.deleteData();
            }
        });
        allert.setNegativeButton("nie", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                return;
            }
        });
        AlertDialog alertDialog = allert.create();
        alertDialog.show();
    }

    /**
     * Method send mail with xls file
     * @param sqLiteExcel
     */
    public void sendMail(SQLiteExcel sqLiteExcel) {
        File file = new File(sqLiteExcel.getXslFile());
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("application/message");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{""});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "MySubject");
        Uri URI = FileProvider.getUriForFile(this, getApplicationContext().getPackageName() + ".provider", file);
        mailIntent.putExtra(Intent.EXTRA_STREAM, URI);
        startActivity(Intent.createChooser(mailIntent, "Send it out!"));

    }

}
