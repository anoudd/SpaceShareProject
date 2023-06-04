package com.example.spaceshareproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;
//main
public class HomeActivity extends AppCompatActivity {


    private Button firstButton, secondButton;
   String user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //current user name
        user = getIntent().getStringExtra("login_username");
        // Initialize buttons
        firstButton = findViewById(R.id.first_button);
        secondButton = findViewById(R.id.second_button);


        // Set click listeners for buttons
        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,myOffice.class);
                intent.putExtra("login_username", user);
                startActivity(intent);
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,  otherOffices.class);
                intent.putExtra("login_username", user);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}















