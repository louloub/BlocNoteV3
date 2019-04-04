package com.example.profilRTDBtoCF.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.profilRTDBtoCF.Profile;
import com.example.profilRTDBtoCF.R;

import java.util.List;

public class Adapter extends ArrayAdapter<Profile> {

    // Constructeur Adapter
    // Appeler par "NoteListe" avec en paramètres le context + la liste des profiles
    public Adapter(Context mContext, Profile profiles)
    {
        // Super : en référence à la class EXTENDS : "ArrayAdapter"
        super(mContext,R.layout.content_adapter_liste, (List<Profile>) profiles);
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
            }
        });

        return convertView;
    }
}