package com.example.profilRTDBtoCF.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.profilRTDBtoCF.CreateNote;
import com.example.profilRTDBtoCF.Profile;
import com.example.profilRTDBtoCF.R;

import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;

public class AdapterListe extends ArrayAdapter<Profile> {

    // Constructeur Adapter
    // Appeler par "NoteListe" avec en paramètres le context + la liste des profiles
    public AdapterListe(Context mContext, List<Profile> profiles)
    {
        // Super : en référence à la class EXTENDS : "ArrayAdapter"
        super(mContext,R.layout.content_adapter_liste, profiles);
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
        final TextView descriptionTextView = convertView.findViewById(R.id.note_description);

        titleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Récupérer la profile sur laquelle on click
                /*
                Profile currentProfile = getItem(position);
                String uid = currentProfile.getUid();
                String title = currentProfile.getTitle();
                String description = currentProfile.getDescription();

                Intent intent = new Intent(getContext(), CreateNote.class);
                intent.putExtra("uid", uid);
                intent.putExtra("profile", title);
                intent.putExtra("description", description);
                startActivity(getContext(),intent,Bundle.EMPTY);
                */
            }
        });

        /*

        Profile profile = getItem(position);

        String str = profile.getTitle();
        String desc = profile.getDescription();

        titleTextView.setText(str);
        descriptionTextView.setText(desc);

        */

        // Récupérer la postiion de chaque item
        // Profile currentNote = getItem(position);

        // String noteText = currentNote.getTitle();

        // Je prends le text de "currentNote" et je l'affiche sur le textView de la ligne correspondante
        // titleTextView.setText(noteText);

        return convertView;
    }
}