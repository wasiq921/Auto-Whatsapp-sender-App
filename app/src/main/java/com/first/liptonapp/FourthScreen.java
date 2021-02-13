package com.first.liptonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.firebase.database.core.utilities.Validation;

public class FourthScreen extends AppCompatActivity {

    public CheckBox checkBox;
    public CheckBox checkBox2;
    public CheckBox checkBox3;
    public CheckBox checkBox4;
    public Button fourth_next;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_screen);
        checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        fourth_next = (Button) findViewById(R.id.fourth_next);

        fourth_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                final String Mobile_Number = intent.getStringExtra(MainActivity.EXTRA_NUMBER);
                final String Name = intent.getStringExtra(MainActivity.EXTRA_NAME);
                final String Friend_Name = intent.getStringExtra(MainActivity.EXTRA_FRIEND_NAME);


                if(checkBox.isChecked() && checkBox2.isChecked() && checkBox3.isChecked() && checkBox4.isChecked()) {

                    String message = "Hey " + Friend_Name + "! Your Chai Partner " + Name+ " Has special message for you. /n Play the video to find out!";
                    MySendSMSservice.startActionWHATSAPP(getApplicationContext(), Mobile_Number, message);
                    Intent intent1 = new Intent(FourthScreen.this, FifthScreen.class);
                    startActivity(intent1);


                }
                else{
                    Toast.makeText(FourthScreen.this, "Check All Consent", Toast.LENGTH_LONG).show();
                }
            }
        });








    }






    public void onHome(View view) {
        startActivity(new Intent(this,FirstScreen.class));
    }
}