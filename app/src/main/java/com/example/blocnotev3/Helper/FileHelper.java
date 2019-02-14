package com.example.blocnotev3.Helper;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

public class FileHelper {

    DatabaseReference db;
    Boolean saved=null;
    //ArrayList
    ArrayList<String> ArrayListNote=new ArrayList<>();

    public FileHelper(DatabaseReference db) {
        this.db = db;
    }
    // Définition du nom de la collection
    private static final String COLLECTION_NAME = "notes";
    // Methode qui sauvergarde les données en BDD
    public static Task<DocumentReference> saveToFirebase(String textMessage){

        Map<String, Object> user = new HashMap<>();
        // "first" est la clé qui permet de chercher "textMessage" dans la "HashMap"
        user.put("first", textMessage);

        return Helper.getChatCollection()
                .document("document")
                .collection(COLLECTION_NAME)
                .add(user)
                ;
    }

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