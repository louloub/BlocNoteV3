package com.example.blocnotev3.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.blocnotev3.Adapter.AdapterListe;
import com.example.blocnotev3.CreateNote;
import com.example.blocnotev3.Helper.Helper;
import com.example.blocnotev3.Note;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_liste);

        // ---------
        // Récupération des données dans la BDD
        // ---------

        // Référence dans la BDD : "CollectionReference"
        // "DocumenRefrence" pour les documents uniquement
        CollectionReference chatDocumentNotes = Helper.getChatDocumentNotes();

        chatDocumentNotes.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            // Override : On s'approprie la methode "onComplete" qui est étendue de "AppCompatActivity"
            // onComplete : Called when the Task completes.
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                // Création de l'array List qui contriendra les notes
                ArrayList <Note> noteListe = new ArrayList<>();

                if (task.isSuccessful()) {

                    // Parcourir tous les documents et donner les resultats (getResult)
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        String title = document.getString("first");
                        String description = document.getString("description");
                        String uid = document.getId();

                        Note note = new Note(uid, title, description );
                        noteListe.add(note);
                    }

                    //--------
                    // ADAPTER
                    //--------
                    // Création de l'adapteur view (list view) avec le modele XML
                    ListView ListView = findViewById(R.id.listView);

                    // Implémentation d'un nouvel adapterListe (ArrayAdapter) "mMAdapterList"
                    AdapterListe mMAdapterList = new AdapterListe(NotesListe.this, noteListe);

                    // On associe l'adapter "mMAdapterList "à l'adapter view "ListView" avec "setAdapter"
                    ListView.setAdapter(mMAdapterList);

                    //--------
                    // BOUCLE
                    //--------

                    for(int i = 0; i < noteListe.size(); i++){

                        //i = mMAdapterList.getItem("");

                        String noteTitle = noteListe.get(i).getTitle();
                        String noteDescription = noteListe.get(i).getDescription();

                        Log.d("TAG", "Item " + noteTitle + " " + i + " " + noteDescription);
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