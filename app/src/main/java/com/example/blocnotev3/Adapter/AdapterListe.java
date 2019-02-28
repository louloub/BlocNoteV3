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
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class AdapterListe extends ArrayAdapter<Note> {

    // Constructeur Adapter
    // Appeler par "NoteListe" avec en paramètres le context + la liste des notes
    public AdapterListe(Context mContext, List<Note> notes)
    {
        // Super : en référence à la class EXTENDS : "ArrayAdapter"
        super(mContext,R.layout.content_adapter_liste,notes);
    }

    // Je défini moi meme le comportement de la methode "getView" qui appartient à "ArrayAdapter"
    // View = paramètre de sortie
    // "View getView" est appelé à chaque fois qu'un item est affiché à l'écran
    // position : position de l'item dans la liste
    // parent : layout auquel rattacher la vue
    // convertView : l'ancienne vue à réutiliser si possible
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        // ??
        if(convertView == null){
            convertView = ((Activity)getContext())
                    .getLayoutInflater()
                    .inflate(R.layout.content_adapter_liste,parent,false);
        }

        // Je récupère mon objet "TextView" et je stock dans "titleTextView"
        final TextView titleTextView = convertView.findViewById(R.id.note_title);

        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Récupérer la note sur laquelle on click
                Note currentNote = getItem(position);
                String uid = currentNote.getUid();
                String title = currentNote.getTitle();
                String description = currentNote.getDescription();

                Intent intent = new Intent(getContext(), CreateNote.class);
                intent.putExtra("uid", uid);
                intent.putExtra("note", title);
                intent.putExtra("description", description);
                startActivity(getContext(),intent,Bundle.EMPTY);
            }
        });

        Note note = getItem(position);
        String str = note.getTitle();
        titleTextView.setText(str);

        // Récupérer la postiion de chaque item
        // Note currentNote = getItem(position);

        // String noteText = currentNote.getTitle();

        // Je prends le text de "currentNote" et je l'affiche sur le textView de la ligne correspondante
        // titleTextView.setText(noteText);

        return convertView;
    }
}