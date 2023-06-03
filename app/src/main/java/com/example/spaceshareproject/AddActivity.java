package com.example.spaceshareproject;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText name_input, price_input, size_input;
    Button add_button;
    String userName,seekerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        // from my office
        userName = getIntent().getStringExtra("login_username");
         seekerName ="none";
        name_input = findViewById(R.id.name_input);
        price_input = findViewById(R.id.price_input);
        size_input = findViewById(R.id.size_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addOffice(name_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()),
                        Integer.valueOf(size_input.getText().toString().trim()),userName,seekerName);
                name_input.setText("");
                price_input.setText("");
                size_input.setText("");
            }
        });
    }
}

