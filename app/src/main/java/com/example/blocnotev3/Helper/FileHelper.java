package com.example.blocnotev3.Helper;

import android.content.Intent;

import com.example.blocnotev3.Note;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;

public class FileHelper {

    DatabaseReference db;
    Boolean saved=null;

    //ArrayList
    ArrayList<String> ArrayListNote=new ArrayList<>();

    public FileHelper(DatabaseReference db) {
        this.db = db;
    }

    // Définition du nom de la collection
    private static final String NOTE_COLLECTION = "notes";

    // ------
    // READ Firebase
    // ------

    public void fetchData(DataSnapshot noteSnapshot)
    {
        ArrayListNote.clear();

        // "ds" : parcourir tous les noeuds (children) de la collection "notes"
        for (DataSnapshot ds : noteSnapshot.getChildren())
        {
            // "name" récupère la valeur correspondant à la clé "first" qu'on récupère en "string"
            String name = ds.child("first").getValue(String.class);
            // une fois récupéré on l'ajoute à la liste de note
            ArrayListNote.add(name);
        }
    }
}