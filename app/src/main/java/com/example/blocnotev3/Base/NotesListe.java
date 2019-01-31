package com.example.blocnotev3.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blocnotev3.CreateNote;
import com.example.blocnotev3.Helper.FileHelper;
import com.example.blocnotev3.Helper.Helper;
import com.example.blocnotev3.MainActivity;
import com.example.blocnotev3.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NotesListe extends AppCompatActivity {

    // Firabase
    private FirebaseAuth mAuth;
    // ListView
    ListView listView;
    // Button Floating
    FloatingActionButton floatingActionButton;
    // Création de l'array List qui contriendra les notes en "String"
    private ArrayList <String> noteListe = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_liste);

        // ListView
        listView = findViewById(R.id.listView);

        // ---------
        // Récupération des données dans la BDD
        // ---------

        // Référence dans la BDD : "CollectionReference"
        // "DocumenRefrence" pour les documents uniquement
        CollectionReference chatDocumentNotes = Helper.getChatDocumentNotes();

        chatDocumentNotes.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    // Parcourir tous les documents et donner les resultats (getResult)
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String stringNote = document.getString("first");
                        noteListe.add(stringNote);
                    }
                } else {
                }
            }
        });

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
}

