package com.hfad.dzienniczekseniora.database;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.ajts.androidmads.library.SQLiteToExcel;

import java.io.File;

public class SQLiteExcel {
    Context context;
    String fileName = "excel.xsl";
    String directory_path = Environment.getExternalStorageDirectory().getPath() + "/backup/";
    String directory_path2 = Environment.getExternalStorageDirectory().getPath() + "/backup/"+fileName;
    public SQLiteExcel(Context context) {
        this.context = context;
    }

    public void ifFileExistsAndCreate() {
        DbHelper dbHelper = new DbHelper(context);
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }
        SQLiteToExcel sqliteToExcel2 = new SQLiteToExcel(context, dbHelper.getDatabaseName(), directory_path);
        sqliteToExcel2.exportAllTables(fileName, new SQLiteToExcel.ExportListener() {

            @Override
            public void onStart() {
                Log.d("start", "Started Exported");

            }

            @Override
            public void onCompleted(String filePath) {
                Log.d("succesfully", "Successfully Exported");
            }

            @Override
            public void onError(Exception e) {
                Log.d("error", e.getMessage());

            }
        });
    }

    public void checkIfXslCreated() {
        final File extStore = Environment.getExternalStorageDirectory();
        File myFile = new File(extStore.getAbsolutePath() + "/backup/"+fileName);

        if (myFile.exists()) {
            Log.d("YES", "YES");
        } else {
            Log.d("NO", "NO");
        }
    }

    public Intent sendEmail() {
        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.setType("application/message");
        mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"janekpro996@gmail.com"});
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "MySubject");
        Uri URI = Uri.parse(directory_path2);
        mailIntent.putExtra(Intent.EXTRA_STREAM, URI);
        return mailIntent;
    }
}
