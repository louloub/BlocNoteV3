package com.example.blocnotev3.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.blocnotev3.Base.NotesListe;
import com.example.blocnotev3.CreateNote;
import com.example.blocnotev3.Note;
import com.example.blocnotev3.R;
import java.util.ArrayList;

import static android.support.v4.content.ContextCompat.startActivity;

public class AdapterListe extends ArrayAdapter<Note> {


    // Constructeur Adapter
    public AdapterListe(Context mContext, ArrayList<Note> mOriginalValues){
        super(mContext,R.layout.content_adapter_liste,mOriginalValues);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView = ((Activity)getContext())
                    .getLayoutInflater()
                    .inflate(R.layout.content_adapter_liste,parent,false);
        }


        final TextView titleTextView = convertView.findViewById(R.id.note_title);
        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Récupérer la note sur laquelle on click
                Note currentNote = getItem(position);
                String uid = currentNote.getUid();
                String title = currentNote.getTitle();

                Intent intent = new Intent(getContext(), CreateNote.class);
                intent.putExtra("uid", uid);
                intent.putExtra("note", title);
                startActivity(getContext(),intent,Bundle.EMPTY);

                // INTENT


            }
        });

        // Récupérer la postiion de chaque item
        Note currentNote = getItem(position);

        // String uid = dataSnapshot.getKey();

        titleTextView.setText(currentNote.getTitle());

        return convertView;
    }
/*
    public void startActivity(Intent intent) {
        this.startActivity(intent);
    }

    Intent intent = new Intent(getContext(), CreateNote.class);
                startActivity(this, intent, Bundle options);
*/

}