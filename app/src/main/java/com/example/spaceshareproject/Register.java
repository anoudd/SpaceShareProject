package com.example.spaceshareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText username, password,firstname ,lastname, phonenumber;
    Button signup;
    DataBase3 DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        firstname = findViewById(R.id.firstname);
        lastname = findViewById(R.id.lastname);
        phonenumber = findViewById(R.id.phonenumber);

        signup = findViewById(R.id.btnsignup);
        //  signin = findViewById(R.id.btnsignin);
        DB = new DataBase3(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String first  = firstname.getText().toString();
                String last  = lastname.getText().toString();
                String phone  = phonenumber.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(Register.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert=false;
                    Boolean checkUser= DB.checkUsername(user);
                    Boolean validInfo = false;
                    if ( DB.checkPassword(pass) && DB.checkPhone(phone))
                        validInfo =true;


                    if(!checkUser){
                        if (validInfo) {
                            insert = DB.insertData(phone, pass, first, user, last);
                        }
                        if(insert){
                            Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else {
                            if (!DB.checkPassword(pass))
                                Toast.makeText(Register.this, "Enter a strong password", Toast.LENGTH_SHORT).show();
                            else if (!DB.checkPhone(phone))
                                Toast.makeText(Register.this, "Enter a correct phone number", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Register.this,"Already exists! please sign in",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });





    }

}