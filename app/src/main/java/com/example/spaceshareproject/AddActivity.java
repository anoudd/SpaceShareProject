package com.example.spaceshareproject;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddActivity extends AppCompatActivity {

    EditText name_input, price_input, size_input;
    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        price_input = findViewById(R.id.price_input);
        size_input = findViewById(R.id.size_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(name_input.getText().toString().trim(),
                        Integer.valueOf(price_input.getText().toString().trim()),
                        Integer.valueOf(size_input.getText().toString().trim()));
            }
        });
    }
}
