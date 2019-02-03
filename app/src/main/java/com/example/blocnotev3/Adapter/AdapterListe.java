package com.example.blocnotev3.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.blocnotev3.Notes;
import com.example.blocnotev3.R;

import java.util.ArrayList;
import java.util.List;

import static java.security.AccessController.getContext;

public class AdapterListe extends ArrayAdapter<String> {

    // Constructeur Adapter
    public AdapterListe(Context mContext, ArrayList<String> mOriginalValues){
        super(mContext,R.layout.content_adapter_liste,mOriginalValues);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView =  ((Activity)getContext()).getLayoutInflater().inflate(R.layout.content_adapter_liste,parent,false);
        }

        TextView titleTextView = convertView.findViewById(R.id.note_title);

        // Récupérer la postiion de chaque item
        String item = getItem(position);

        titleTextView.setText(item);

        return convertView;
    }
}