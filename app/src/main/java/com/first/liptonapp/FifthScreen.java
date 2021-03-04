package com.first.liptonapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.ajts.androidmads.library.SQLiteToExcel;

import java.io.File;

public class FifthScreen extends AppCompatActivity {
    SQLiteToExcel sqliteToExcel;
    String directory_path = Environment.getExternalStorageDirectory().getPath() + "/Backup/";
    public Button export_data;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fifth_screen);
        export_data  = (Button) findViewById(R.id.export_data);

        try {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // this will request for permission from the user if not yet granted
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            } else {
                // Download code here
                System.out.println("NOt ok");
            }
        }catch (Exception e){
            System.out.println(e);
        }

        File file = new File(directory_path);   //check if there any file in this directory path
        if (!file.exists()) {
            file.mkdirs();
        }
        export_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Export SQLite DB as EXCEL FILE
                sqliteToExcel = new SQLiteToExcel(getApplicationContext(), DBManager.dbname, directory_path);
                sqliteToExcel.exportAllTables("student.xls", new SQLiteToExcel.ExportListener() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onCompleted(String filePath) {
                        Utils.showSnackBar(view, "Successfully Exported");
                        // Toast.makeText(MainActivity.this, "Export successful!", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Exception e) {
                        e.printStackTrace();
                        //Toast.makeText(MainActivity.this, "No Export", Toast.LENGTH_SHORT).show();
                        Utils.showSnackBar(view, "Not Exported");
                    }
                });

                try {

                    String filelocation= Environment.getExternalStorageDirectory().getPath() + "/Backup/student.xls";
                    Intent intent = new Intent(Intent.ACTION_SENDTO);
                    intent.setType("text/plain");
                    String message="File to be shared is .";
                    intent.putExtra(Intent.EXTRA_SUBJECT, "Students REPORT");
                    intent.putExtra(Intent.EXTRA_STREAM, Uri.parse( "file://"+filelocation));
                    intent.putExtra(Intent.EXTRA_TEXT, message);
                    intent.setData(Uri.parse("mailto:wasiq921@gmail.com"));   //insert your email address("mailto:csent.company@gmail.com")
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    startActivity(intent);
                } catch(Exception e)  {
                    System.out.println("is exception raises during sending mail"+e);
                }


            }
        });

    }

    public void onHome(View view) {
        startActivity(new Intent(this,FirstScreen.class));
    }
}