package com.first.liptonapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.accessibilityservice.AccessibilityService;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.obsez.android.lib.filechooser.ChooserDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NUMBER = "com.exapmle.lipton.app.EXTRA_NUMBER";
    public static final String EXTRA_NAME = "com.example.lipton.app.EXTRA_NAME";
    public static final String EXTRA_FRIEND_NAME = "com.example.lipton.app.EXTRA_FRIEND_NAME";
    public EditText Enter_Name;

    public EditText Enter_Mobile_Number;
    public EditText Enter_Email;
    public EditText Enter_Age;

    public Button button_whatsapp;
    public EditText Enter_Friend_Name;
    public Button button_attachment;
    public Button button_viewData;


    //Member member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Enter_Name = (EditText) findViewById(R.id.Enter_Name);
        Enter_Mobile_Number = (EditText) findViewById(R.id.Enter_Mobile_Number);
        Enter_Email = (EditText) findViewById(R.id.Enter_Email);
        Enter_Age = (EditText) findViewById(R.id.Enter_Age);
        Enter_Friend_Name = (EditText) findViewById(R.id.Enter_Friend_Name);
        button_viewData = (Button) findViewById(R.id.button_viewData);

        button_whatsapp = findViewById(R.id.button_whatsapp);


       // button_attachment = findViewById(R.id.button_attachment);

      //  member = new Member();



        /*if(!isAccessibilityOn(getApplicationContext())){
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }*/

        /*if (!isAccessibilityOn (context, WhatsAppAccessibilityService)) {
            Intent intent = new Intent (Settings.ACTION_ACCESSIBILITY_SETTINGS);
            context.startActivity (intent);
        }*/







        /*button_attachment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String path = null;
                ChooserDialog dialog2 = new ChooserDialog(MainActivity.this)
                        .withStartFile(path)
                        .withChosenListener(new ChooserDialog.Result() {
                            @Override
                            public void onChoosePath(String path, File pathFile) {
                                Toast.makeText(MainActivity.this, "FILE: " + path, Toast.LENGTH_SHORT).show();
                            }
                        })
                        // to handle the back key pressed or clicked outside the dialog:
                        .withOnCancelListener(new DialogInterface.OnCancelListener() {
                            public void onCancel(DialogInterface dialog) {
                                Log.d("CANCEL", "CANCEL");
                                dialog.cancel(); // MUST have
                            }
                        })
                        .build()
                        .show();
            }
        });*/
    }

    public void onHome(View view) {
        startActivity(new Intent(this,FirstScreen.class));
    }

    public void onClick(View view) {


            DBManager sqLiteDatabase = new DBManager(this);
            boolean res = sqLiteDatabase.addRecord(Enter_Name.getText().toString(),Enter_Mobile_Number.getText().toString()
                    ,Enter_Email.getText().toString(),Enter_Age.getText().toString(),Enter_Friend_Name.getText().toString());

        if (res = true){
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();

        }else {
            Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_LONG).show();
        }

            String Mobile_Number = Enter_Mobile_Number.getText().toString();
            String Name = Enter_Name.getText().toString();
            String Friend_Name = Enter_Friend_Name.getText().toString();

            Intent intent = new Intent(MainActivity.this, FourthScreen.class);
            intent.putExtra(EXTRA_NUMBER, Mobile_Number);
            intent.putExtra(EXTRA_NAME, Name);
            intent.putExtra(EXTRA_FRIEND_NAME, Friend_Name);
            startActivity(intent);

        //Toast.makeText(MainActivity.this, "Data Inserted Successfuly",Toast.LENGTH_LONG).show();






        /*FirebaseDatabase reff = FirebaseDatabase.getInstance();
        DatabaseReference root = reff.getReference().child("Users");

        String name = Enter_Name.getText().toString();
        String number = Enter_Mobile_Number.getText().toString();
        String email = Enter_Email.getText().toString();
        String age = Enter_Age.getText().toString();
        String friend = Enter_Friend_Name.getText().toString();


        UserHelperClass helperClass= new UserHelperClass(name, number, email, age, friend);

        root.child(number).setValue(helperClass);

                DatabaseReference root = reff.getReference().child("Name");

                DatabaseReference root2 = reff.getReference().child("Mobile Number");
                DatabaseReference root3 = reff.getReference().child("Age");
                DatabaseReference root4 = reff.getReference().child("Email");
                DatabaseReference root5 = reff.getReference().child("Friend Name");*/

                /*int age = Integer.parseInt(Enter_Age.getText().toString().trim());
               member.setName(Enter_Name.getText().toString().trim());
               member.setMobile_Number(Enter_Mobile_Number.getText().toString().trim());
               member.setAge(age);
               member.setEmail(Enter_Email.getText().toString().trim());
               member.setFriend_Name(Enter_Friend_Name.getText().toString().trim());*/


              /*  root2.setValue(Enter_Mobile_Number.getText().toString());
                Enter_Mobile_Number.setText("");
                root3.setValue(Enter_Age.getText().toString());
                Enter_Age.setText("");
                root4.setValue(Enter_Email.getText().toString());
                Enter_Email.setText("");
                root5.setValue(Enter_Friend_Name.getText().toString());
                Enter_Friend_Name.setText(""); */






                /*String message = "My Name is Wasiq" + Enter_Mobile_Number.getText().toString();
                MySendSMSservice.startActionWHATSAPP(getApplicationContext(), Enter_Mobile_Number, message);*/
    }


            public void viewData(View v) {
                DBManager sqLiteDatabase = new DBManager(this);
                Cursor result = sqLiteDatabase.getAllData();
                if(result.getCount()==0){                     //when there is no data show message..error message is depend on  "showMessage"method
                    //show message
                    showMessage("Error","Nothing Found");
                    return;

                }
                StringBuffer buffer=new StringBuffer();

                while(result.moveToNext()){                                               //read and collect data in database in coloum
                    buffer.append("ID :"+ result.getString(0)+"\n");
                    buffer.append("Name :"+ result.getString(1)+"\n");
                    buffer.append("MobileNumber :"+ result.getString(2)+"\n");
                   buffer.append("Email :"+ result.getString(3)+"\n");
                    buffer.append("Age :"+ result.getString(4)+"\n");
                    buffer.append("Friend Name :"+ result.getString(5)+"\n\n");
                    //   buffer.append("Date :"+ res.getString(4)+"\n\n");


                }
                //show all data
                showMessage("Data of Database",buffer.toString());                  //show all data in list.list is depnd on  "showMessage"method
            }






    public void showMessage(String title1,String Message1){                   //show message method
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title1);
        builder.setMessage(Message1);
        builder.show();

    }

        private boolean isAccessibilityOn (Context context, Class < ? extends
        AccessibilityService > clazz){
            int accessibilityEnabled = 0;
            final String service = context.getPackageName() + "/" + clazz.getCanonicalName();
            try {
                accessibilityEnabled = Settings.Secure.getInt(context.getApplicationContext().getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
            } catch (Settings.SettingNotFoundException ignored) {
            }

            TextUtils.SimpleStringSplitter colonSplitter = new TextUtils.SimpleStringSplitter(':');

            if (accessibilityEnabled == 1) {
                String settingValue = Settings.Secure.getString(context.getApplicationContext().getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
                if (settingValue != null) {
                    colonSplitter.setString(settingValue);
                    while (colonSplitter.hasNext()) {
                        String accessibilityService = colonSplitter.next();

                        if (accessibilityService.equalsIgnoreCase(service)) {
                            return true;
                        }
                    }
                }
            }

            return false;

        }




    }
