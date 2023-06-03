package com.example.spaceshareproject;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
//seeker
public class otherOffices extends AppCompatActivity {
    RecyclerView recyclerView;

    ImageView empty_imageview;
    TextView no_data;
    String user;
    MyDatabaseHelper myDB;
    ArrayList<String> office_id, office_name, office_price, office_size,OwnerName,seekerName,availability;
    CustomAdapter2 customAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_offices1);
        user = getIntent().getStringExtra("login_username");
        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);


        myDB = new MyDatabaseHelper(otherOffices.this);
        office_id = new ArrayList<>();
        office_name = new ArrayList<>();
        office_price = new ArrayList<>();
        office_size = new ArrayList<>();
        OwnerName = new ArrayList<>();
        seekerName =new ArrayList<>();
        availability =new ArrayList<>();
        storeDataInArrays();

        customAdapter2 = new CustomAdapter2(otherOffices.this,this, office_id, office_name, office_price,
                office_size,OwnerName,seekerName,availability,user);
        recyclerView.setAdapter(customAdapter2);
        recyclerView.setLayoutManager(new LinearLayoutManager(otherOffices.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {

                String ownerName = cursor.getString(5);

                // Check if the logged-in user is the owner of the office

                office_id.add(cursor.getString(0));
                office_name.add(cursor.getString(1));
                office_price.add(cursor.getString(2));
                office_size.add(cursor.getString(3));
                availability.add(cursor.getString(4));
                OwnerName.add(cursor.getString(5));
                seekerName.add(cursor.getString(6));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logoutmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.logout){
            Intent intentL = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intentL);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}
