package com.example.spaceshareproject;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Dialog extends AppCompatDialogFragment {
private EditText name,duration;
private DialogListener listener;

    @NonNull
    @Override
    public android.app.Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder b= new AlertDialog.Builder(getActivity());
        LayoutInflater l=getActivity().getLayoutInflater();
        View view= l.inflate(R.layout.rentdialog,null);
        b.setView(view)
        .setTitle("Rent Form")
        .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        b.setPositiveButton("submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               String n=name.getText().toString();
               String d=duration.getText().toString();
               listener.applyText(n,d);
            }
        });
        name= view.findViewById(R.id.rentername);
        duration= view.findViewById(R.id.duration);
        return b.create();

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener=(DialogListener) context;
        } catch (ClassCastException e) {
            Toast.makeText(context, "in exception", Toast.LENGTH_SHORT).show();
        }
    }

    public interface DialogListener{

        void applyText(String name,String duration);

}

}



