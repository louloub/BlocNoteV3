package com.example.profilRTDBtoCF.Listener;

import com.example.profilRTDBtoCF.Note;

import java.util.ArrayList;

public interface DataBaseServiceListener {
    void onNoteListeLoadedFromRTDB(ArrayList<Note> loadedNotes);
}
