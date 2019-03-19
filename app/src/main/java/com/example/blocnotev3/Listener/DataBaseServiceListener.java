package com.example.blocnotev3.Listener;

import com.example.blocnotev3.Note;

import java.util.ArrayList;

public interface DataBaseServiceListener {
    void onNoteListeLoadedFromRTDB(ArrayList<Note> loadedNotes);

    // méthode au cas ou pas de liste

}
