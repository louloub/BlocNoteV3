package com.example.profilRTDBtoCF.Listener;

import com.example.profilRTDBtoCF.Note;

import java.util.ArrayList;

public interface CloudFirestoreServiceListener {
    void onNoteListeLoadedFromCF(ArrayList<Note> loadedNotesCF);
    void onNoteListNotFoundInCF();
}

