package com.example.spaceshareproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
        RecyclerView recyclerView;
        FloatingActionButton add_button;
        ImageView empty_imageview;
        TextView no_data;

        MyDatabaseHelper myDB;
        ArrayList<String> office_id, office_name, office_price, office_size;
        CustomAdapter customAdapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);

            recyclerView = findViewById(R.id.recyclerView);
            add_button = findViewById(R.id.add_button);
            empty_imageview = findViewById(R.id.empty_imageview);
            no_data = findViewById(R.id.no_data);
            add_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                    startActivity(intent);
                }
            });

            myDB = new MyDatabaseHelper(HomeActivity.this);
            office_id = new ArrayList<>();
            office_name = new ArrayList<>();
            office_price = new ArrayList<>();
            office_size = new ArrayList<>();

            storeDataInArrays();

            customAdapter = new CustomAdapter(HomeActivity.this,this, office_id, office_name, office_price,
                    office_size);
            recyclerView.setAdapter(customAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if(requestCode == 1){
                recreate();
            }
        }

        void storeDataInArrays(){
            Cursor cursor = myDB.readAllData();
            if(cursor.getCount() == 0){
                empty_imageview.setVisibility(View.VISIBLE);
                no_data.setVisibility(View.VISIBLE);
            }else{
                while (cursor.moveToNext()){
                    office_id.add(cursor.getString(0));
                    office_name.add(cursor.getString(1));
                    office_price.add(cursor.getString(2));
                    office_size.add(cursor.getString(3));
                }
                empty_imageview.setVisibility(View.GONE);
                no_data.setVisibility(View.GONE);
            }
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.my_menu, menu);
            return super.onCreateOptionsMenu(menu);
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            if(item.getItemId() == R.id.delete_all){
                confirmDialog();
            }
            return super.onOptionsItemSelected(item);
        }

        void confirmDialog(){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Delete All?");
            builder.setMessage("Are you sure you want to delete all Data?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    MyDatabaseHelper myDB = new MyDatabaseHelper(HomeActivity.this);
                    myDB.deleteAllData();
                    //Refresh Activity
                    Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            builder.create().show();
        }
    }
