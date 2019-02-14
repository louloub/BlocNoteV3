package com.example.blocnotev3.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blocnotev3.MainActivity;
import com.example.blocnotev3.Notes;
import com.example.blocnotev3.R;

import java.util.ArrayList;
import java.util.List;

import static com.facebook.share.widget.ShareDialog.show;
import static com.firebase.ui.auth.AuthUI.getApplicationContext;
import static java.security.AccessController.getContext;

public class AdapterListe extends ArrayAdapter<String> {

    private MainActivity activity;

    // Constructeur Adapter
    public AdapterListe(Context mContext, ArrayList<String> mOriginalValues){
        super(mContext,R.layout.content_adapter_liste,mOriginalValues);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = ((Activity)getContext()).getLayoutInflater().inflate(R.layout.content_adapter_liste,parent,false);
        }

        TextView titleTextView = convertView.findViewById(R.id.note_title);

        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myPopup = new AlertDialog.Builder(activity);
                myPopup.setTitle("Salut");
                myPopup.setMessage("Salut les gens c'est ton téléphone qui te parle");
                myPopup.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Vous avez cliquez sur Oui", Toast.LENGTH_SHORT).show();
                    }
                });

                myPopup.setNegativeButton("Non", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Vous avez cliquez sur Non", Toast.LENGTH_SHORT).show();
                    }

                });

                myPopup.show();
            }
        });

        // Récupérer la postiion de chaque item
        String item = getItem(position);

        titleTextView.setText(item);

        return convertView;
    }
}