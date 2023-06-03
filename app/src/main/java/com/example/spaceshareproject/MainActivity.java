package com.example.spaceshareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText login_username, login_password;
    Button login_button, login_gotoR;
    RadioButton radio_button;
    MyDatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_username = findViewById(R.id.login_username);
        login_password = findViewById(R.id.login_password);
        login_button = findViewById(R.id.login_button);
        login_gotoR = findViewById(R.id.login_gotoR);
        radio_button=findViewById(R.id.radioButton);
        DB = new MyDatabaseHelper(this);

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = login_username.getText().toString();
                String pass = login_password.getText().toString();
                Boolean RadioButtonState = radio_button.isChecked();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean check = DB.checkUsernamePassword(user, pass);
                    if (check){

                        Toast.makeText(MainActivity.this, "Sign in successfully ", Toast.LENGTH_SHORT).show();
                        Intent intent;
                        if(RadioButtonState){
                        intent = new Intent(getApplicationContext(),HomeActivity.class);
                        startActivity(intent);}
                             else{intent = new Intent(getApplicationContext(),HomeActivity2.class);
                            startActivity(intent);}

                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        login_gotoR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });


    }
}

