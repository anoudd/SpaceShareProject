
package com.example.spaceshareproject;

import android.annotation.SuppressLint;
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

    private static Context context;
    private Activity activity;
    private ArrayList office_id, office_name, office_price, office_size, OwnerName,textview;
    private String currentUser;

    CustomAdapter(Activity activity, Context context, ArrayList office_id, ArrayList office_name, ArrayList office_price,
                  ArrayList office_size, ArrayList OwnerName, String user, ArrayList tv){
        this.activity = activity;
        this.context = context;
        this.office_id = office_id;
        this.office_name = office_name;
        this.office_price = office_price;
        this.office_size = office_size;
        this.OwnerName = OwnerName;
        this.currentUser = user;
        this.textview=tv;
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


    public void onBindViewHolder(@NonNull final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        int minSize = Math.min(Math.min(office_id.size(), office_name.size()), Math.min(office_price.size(), office_size.size()));
        if (position < minSize && !OwnerName.isEmpty()) {
            String ownerName = String.valueOf(OwnerName.get(position));
            if (ownerName.equals(currentUser)) {
                // The owner name for this office matches the current user
                // Set the text for the views in the MyViewHolder instance
                holder.office_id_txt.setText(String.valueOf(office_id.get(position)));
                holder.office_name_txt.setText(String.valueOf(office_name.get(position)));
                holder.office_price_txt.setText(String.valueOf(office_price.get(position)));
                holder.office_size_txt.setText(String.valueOf(office_size.get(position)));

                // Set the onClickListener for the mainLayout
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(context, UpdateActivity.class);

                        intent.putExtra("tv", String.valueOf(textview.get(position)));
                        intent.putExtra("id", String.valueOf(office_id.get(position)));
                        intent.putExtra("name", String.valueOf(office_name.get(position)));
                        intent.putExtra("price", String.valueOf(office_price.get(position)));
                        intent.putExtra("size", String.valueOf(office_size.get(position)));
                        activity.startActivityForResult(intent, 1);
                    }
                });
            } else {
                // The owner name for this office does not match the current user
                // Hide the views for this office
                holder.itemView.setVisibility(View.GONE);
                holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(0, 0));
            }
        }
    }

    @Override
    public int getItemCount() {
        return office_id.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView office_id_txt, office_name_txt, office_price_txt, office_size_txt,OwnerName_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            office_id_txt = itemView.findViewById(R.id.office_id_txt);
            office_name_txt = itemView.findViewById(R.id.office_name_txt);
            office_price_txt = itemView.findViewById(R.id.office_price_txt);
            office_size_txt = itemView.findViewById(R.id.office_size_txt);
            OwnerName_txt=  itemView.findViewById(R.id.OwnerName_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
