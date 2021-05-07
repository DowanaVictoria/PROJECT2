package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword,email;
    Button btnSignUp, btnSignIn;

    DbHelper myDb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        email= (EditText) findViewById(R.id.email);
        btnSignUp =(Button) findViewById(R.id.btnSignUp);
        btnSignIn =(Button) findViewById(R.id.btnSignIn);


        myDb = new DbHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                String ems = email.getText().toString();

                if (user.equals(" ") || pass.equals(" ") || repass.equals(" ") || ems.equals(" ")) {
                    Toast.makeText(MainActivity.this, "Fill all the fields.", Toast.LENGTH_SHORT).show();

                } else {
                    if (pass.equals(repass)) {
                        Boolean userCheckResult = myDb.checkusername(user);
                         if (userCheckResult == false) {
                        Boolean regResults = myDb.insertData(user, pass, repass, ems);
                        if (regResults == true) {
                            Toast.makeText(MainActivity.this, "Registration Sucessful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), SIGNUP.class);
                             startActivity(intent);

                        } else {
                            Toast.makeText(MainActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(MainActivity.this, "User already exists. \n please sigin", Toast.LENGTH_SHORT).show();

                    }
                }else{
                    Toast.makeText(MainActivity.this, "Password not matching.", Toast.LENGTH_SHORT).show();

                }
            }

        }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent =new Intent(getApplicationContext(),SIGNUP.class);
                startActivity(intent);
            }
        });
    }
}




