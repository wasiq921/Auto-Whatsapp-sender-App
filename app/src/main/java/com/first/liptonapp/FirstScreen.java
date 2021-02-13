package com.first.liptonapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

public class FirstScreen extends AppCompatActivity {
    public Button first_next;
    public TextView baname;
    public AlertDialog namedialog;
    public AlertDialog locationdialog;
    public EditText editBAname;
    public EditText editBAlocation;
    public TextView balocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        baname = (TextView) findViewById(R.id.baname);
        balocation = (TextView) findViewById(R.id.balocation);
        namedialog = new AlertDialog.Builder(this).create();
        locationdialog = new AlertDialog.Builder(this).create();
        editBAname = new EditText(this);
        editBAlocation = new EditText(this);

        namedialog.setTitle("Edit BA Name ");
        namedialog.setView(editBAname);



        namedialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                baname.setText(editBAname.getText());
               // balocation.setText(editBAlocation.getText());


            }
        });

        baname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editBAname.setText(baname.getText());
                namedialog.show();
            }
        });

        locationdialog.setTitle("Enter Location");
        locationdialog.setView(editBAlocation);

        locationdialog.setButton(DialogInterface.BUTTON_POSITIVE, "SAVE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                balocation.setText(editBAlocation.getText());
            }
        });

        balocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editBAlocation.setText(balocation.getText());
                locationdialog.show();
            }
        });

        //first_next = findViewById(R.id.first_next);

    }


    public void onClick(View view) {
        /*Intent intent = new Intent(FirstScreen.this,SecondScreen.class);
        startActivity(intent);*/
        new DBManager(this);
        startActivity(new Intent(this,MainActivity.class));
    }

    /*public void startdbapp(View view) {
        new DBManager(this);
        startActivity(new Intent(this,MainActivity.class));
    }*/
}