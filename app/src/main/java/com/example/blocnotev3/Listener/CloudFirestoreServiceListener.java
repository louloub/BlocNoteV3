package com.example.blocnotev3.Listener;

import com.example.blocnotev3.Note;

import java.util.ArrayList;

public interface CloudFirestoreServiceListener {
    void onNoteListeLoadedFromCF(ArrayList<Note> loadedNotesCF);

    // méthode au cas ou pas de liste :
    void onNoteListNotFoundInCF();
}

