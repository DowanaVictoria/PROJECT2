package com.example.myandroidapp;

import androidx.appcompat.app.AppCompatActivity;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SIGNUP extends AppCompatActivity {

    EditText username, password,email;
    Button Textlogin;
    TextView TextViewlogin;
    DbHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_i_g_n_u_p);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email= (EditText) findViewById(R.id.email);
        Textlogin =(Button) findViewById(R.id.Textlogin);


        myDb = new DbHelper(this);
        Textlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String ems = email.getText().toString();

                if(user.equals(" ")|| pass.equals(" ") || ems.equals(" ") ) {
                    Toast.makeText(SIGNUP.this, "Please enter your credentials.", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean Result = myDb.checkusernamePassword(user,pass);
                    if(Result==true){
                        Intent intent = new Intent(getApplicationContext(),Homepage.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(SIGNUP.this, "Invalid credentials.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}


