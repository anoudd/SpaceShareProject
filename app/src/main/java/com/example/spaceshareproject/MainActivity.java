package com.example.spaceshareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText username, password,firstname ,lastname, phonenumber;
    Button signup;
   DataBase3 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkUser = DB.checkUsername(user);
                    if(!checkUser){
                        Boolean insert = DB.insertData(phone , pass,first ,user, last);
                        if(insert){
                            Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(intent);
                        }else{
                            Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Already exists! please sign in",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }
}