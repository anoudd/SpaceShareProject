package com.example.spaceshareproject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private  static Context context;  // made it staticcc
    private Activity activity;
     private String who;
    private ArrayList office_id, office_name, office_price, office_size;

    CustomAdapter(Activity activity, Context context, ArrayList office_id, ArrayList office_name, ArrayList office_price,
                  ArrayList office_size , String who){
         this.activity = activity;
        this.context = context;
        this.office_id = office_id;
        this.office_name = office_name;
        this.office_price = office_price;
        this.office_size = office_size;
        this.who=who;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.office_id_txt.setText(String.valueOf(office_id.get(position)));
        holder.office_name_txt.setText(String.valueOf(office_name.get(position)));
        holder.office_price_txt.setText(String.valueOf(office_price.get(position)));
        holder.office_size_txt.setText(String.valueOf(office_size.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                if ( who.equals("owner")){
                 intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(office_id.get(position)));
                intent.putExtra("name", String.valueOf(office_name.get(position)));
                intent.putExtra("price", String.valueOf(office_price.get(position)));
                intent.putExtra("size", String.valueOf(office_size.get(position)));
                activity.startActivityForResult(intent, 1);}

                else { intent = new Intent(context, rentActivity.class);
                    intent.putExtra("id", String.valueOf(office_id.get(position)));
                    intent.putExtra("name", String.valueOf(office_name.get(position)));
                    intent.putExtra("price", String.valueOf(office_price.get(position)));
                    intent.putExtra("size", String.valueOf(office_size.get(position)));
                    activity.startActivityForResult(intent, 1);}



            }
        });


    }

    @Override
    public int getItemCount() {
        return office_id.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView office_id_txt, office_name_txt, office_price_txt, office_size_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            office_id_txt = itemView.findViewById(R.id.office_id_txt);
            office_name_txt = itemView.findViewById(R.id.office_name_txt);
            office_price_txt = itemView.findViewById(R.id.office_price_txt);
            office_size_txt = itemView.findViewById(R.id.office_size_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
