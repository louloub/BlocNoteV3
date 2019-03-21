package com.example.profilRTDBtoCF.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.profilRTDBtoCF.Adapter.AdapterListe;
import com.example.profilRTDBtoCF.CreateNote;
import com.example.profilRTDBtoCF.Listener.NotesManagerListener;
import com.example.profilRTDBtoCF.Manager.NotesManager;
import com.example.profilRTDBtoCF.Note;
import com.example.profilRTDBtoCF.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class NotesListe extends AppCompatActivity implements NotesManagerListener {

    // Firabase
    private FirebaseAuth mAuth;

    // ListView
    private ListView listView;

    private AdapterListe mMAdapterList;

    // Button Floating
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_liste);

        // Copie des notes de CF vers RTDB
        // "NotesManager.get_instance()" on appel le manager
        // NotesManager.get_instance().readCFwriteRTDB();

        listView = findViewById(R.id.listView);

        NotesManager.get_instance().setListener(this);
        // récupère la liste de note
        ArrayList<Note> notes = NotesManager.get_instance().getNotes();

        if (notes.size() == 0) {
            // demande au manager de lui amener la liste de note
            NotesManager.get_instance().loadNoteList();
        }
        else {
            mMAdapterList = new AdapterListe(NotesListe.this, notes);
            listView.setAdapter(mMAdapterList);
        }

        //--------
        // ADAPTER
        //--------

        // Création de l'adapteur view (list view) avec le modele XML
        if(mMAdapterList != null)
        {
            listView.setAdapter(mMAdapterList);
        }

        // ---------
        // Boutton Floating
        // ---------

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NotesListe.this, CreateNote.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onNoteListLoaded() {

        ArrayList<Note> notes = NotesManager.get_instance().getNotes();
        // Implémentation d'un nouvel adapterListe (ArrayAdapter) "mMAdapterList"
        mMAdapterList = new AdapterListe(NotesListe.this, notes);

        // On associe l'adapter "mMAdapterList "à l'adapter view "ListView" avec "setAdapter"
        listView.setAdapter(mMAdapterList);

        mMAdapterList.notifyDataSetChanged();

    }
}