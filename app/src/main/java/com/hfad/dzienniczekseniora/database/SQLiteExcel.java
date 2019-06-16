package com.hfad.dzienniczekseniora.database;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.ajts.androidmads.library.SQLiteToExcel;

import java.io.File;

public class SQLiteExcel {
    Context context;
    String fileName = "wyniki.xls";
    String directory_path = Environment.getExternalStorageDirectory().getPath() + "/backup/";
    String directory_path2 = directory_path+fileName;
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

    public String getXslFile(){
        return directory_path2;
    }
}
