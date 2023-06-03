package com.example.spaceshareproject;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class rentActivity extends AppCompatActivity implements Dialog.DialogListener{
    EditText name_input, price_input, size_input,rentername,duration;
    Button rent_button , return_button;
   TextView textview;
    String id, name, price, size,current,ownerName,seekerName,availability;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);

        current = getIntent().getStringExtra("login_username");


        name_input = findViewById(R.id.office_name1);
        price_input = findViewById(R.id.office_price1);
        size_input = findViewById(R.id.offer_size1);

        rent_button = findViewById(R.id.rent_button);
        return_button = findViewById(R.id.return_button);

        rentername=findViewById(R.id.rentername);
        duration=findViewById(R.id.duration);
        textview=findViewById(R.id.renterInfo1);


        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(name);
        }


        rent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });

        return_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog2();
            }
        });
        getAndSetIntentData();
    }



    void getAndSetIntentData(){

        Log.d("asd", getIntent().getStringExtra("id"));;
        Log.d("asd", getIntent().getStringExtra("name"));
        Log.d("asd", getIntent().getStringExtra("price"));
        Log.d("asd", getIntent().getStringExtra("size"));
        if(        getIntent().hasExtra("id")
                && getIntent().hasExtra("name")
                && getIntent().hasExtra("price")
                && getIntent().hasExtra("size")){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            price = getIntent().getStringExtra("price");
            size = getIntent().getStringExtra("size");
            ownerName = getIntent().getStringExtra("ownerName");
            seekerName= getIntent().getStringExtra("seekerName");
            availability =getIntent().getStringExtra("availability");
            if (!seekerName.equals(current)) {
               return_button.setVisibility(View.GONE);
            }
            if (availability.equals("not available")) {
                rent_button.setVisibility(View.GONE);
            }
            name_input.append(name);
            price_input.append(price);
            size_input.append(size);

        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }



    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Rent " + name + " ?");
        builder.setMessage("Are you sure you want to Rent " + name + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(rentActivity.this);

               if( myDB.rent(id,current)){

                     openDialog();

               }
                //finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
    void confirmDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Annnulment " + name + "'s contract ?");
        builder.setMessage("Are you sure you want to annnulment " + name + "'s contract  ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(rentActivity.this);

               if( myDB.annnulment(id)){
           Toast.makeText(rentActivity.this, "Annulment contract successfully!.", Toast.LENGTH_SHORT).show();
                   finish();
                }
                //finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }

    public void openDialog(){

Dialog dialog= new Dialog();
dialog.show(getSupportFragmentManager(),"Rent Form");


    }

    @Override
    public void applyText(String name, String duration) {
        String s="Rented by:"+name+"  duration:"+duration;
        textview.setText(s);

    }

    }