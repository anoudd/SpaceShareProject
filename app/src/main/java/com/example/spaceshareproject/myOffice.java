package com.example.spaceshareproject;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class myOffice extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageview;
    TextView no_data;
    String user;

    MyDatabaseHelper myDB;
    ArrayList<String> office_id, office_name, office_price, office_size,OwnerName,tv;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_office);
        //come from home
        user = getIntent().getStringExtra("login_username");
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(myOffice.this, AddActivity.class);
                intent.putExtra("login_username", user);
                startActivity(intent);
            }
        });



        myDB = new MyDatabaseHelper(myOffice.this);
        office_id = new ArrayList<>();
        office_name = new ArrayList<>();
        office_price = new ArrayList<>();
        office_size = new ArrayList<>();
        OwnerName = new ArrayList<>();
        tv=new ArrayList<>();
        storeDataInArrays();

        customAdapter = new CustomAdapter(myOffice.this,this, office_id, office_name, office_price,
                office_size ,OwnerName,user,tv);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(myOffice.this));

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
                     tv.add(cursor.getString(6));
                    office_id.add(cursor.getString(0));
                    office_name.add(cursor.getString(1));
                    office_price.add(cursor.getString(2));
                    office_size.add(cursor.getString(3));
                    OwnerName.add(cursor.getString(5));

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

        if(item.getItemId() == R.id.logout){
            Intent intentL = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intentL);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


}