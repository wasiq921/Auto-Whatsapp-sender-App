package com.first.liptonapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_Login extends AppCompatActivity {
    public Button btn_login;
    public EditText username;
    public EditText password;

    public String actual_username = "admin1";
    public String actual_password = "lipton786";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__login);
        btn_login = (Button) findViewById(R.id.btn_login);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
    }

    public void onClick(View view) {
        if(username.getText().toString().equals(actual_username) && password.getText().toString().equals(actual_password)) {
            startActivity(new Intent(this, FirstScreen.class));
        }
        else {
            Toast.makeText(User_Login.this, "Username & Password doesn't match", Toast.LENGTH_LONG).show();

        }
    }
}