package com.example.blocnotev3.Manager;

import com.example.blocnotev3.Listener.CloudFirestoreServiceListener;
import com.example.blocnotev3.Listener.DataBaseServiceListener;
import com.example.blocnotev3.Listener.NotesManagerListener;
import com.example.blocnotev3.Note;
import com.example.blocnotev3.Services.DataBaseNoteService;
import com.example.blocnotev3.Services.FirestoreNoteService;


import java.util.ArrayList;

public class NotesManager implements DataBaseServiceListener, CloudFirestoreServiceListener {

    private static NotesManager _instance;
    private ArrayList<Note> notes = new ArrayList<>();

    // Déclaration du "listener" de "NotesManagerListener"
    private NotesManagerListener listener;

    public synchronized static NotesManager get_instance() {
        if (_instance == null) {
            _instance = new NotesManager();
        }
        return _instance;
    }

    // Constructeur du manager
    private NotesManager() {
        // définir les listener
        FirestoreNoteService.setListenerCF(this);
        DataBaseNoteService.setListenerRTDB(this);
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setListener(NotesManagerListener listener) {
        this.listener = listener;
    }

    //---------
    // READ
    //---------

    public void readCFwriteRTDB(){
        DataBaseNoteService.readCFwriteRTDB();
    }

    //---------
    // LOAD
    //---------

    public void loadNoteList() {

        FirestoreNoteService.loadNoteList();
    }

    // On s'assure que la liste est bien loaded avec un LISTENER
    public void listLoaded() {

        if(listener != null)
        {
            listener.onNoteListLoaded();
        }
    }

    //---------
    // LOAD CF
    //---------

    @Override
    public void onNoteListeLoadedFromCF(ArrayList<Note> loadedNotesCF) {
        this.notes = loadedNotesCF;
        // listenerCF.onNoteListeLoadedFromCF(loadedNotesCF);
    }

    @Override
    public void onNoteListNotFoundInCF() {
        DataBaseNoteService.readNoteRTDB();
    }

    //---------
    // LOAD RTDB
    //---------

    @Override
    public void onNoteListeLoadedFromRTDB(ArrayList<Note> loadedNotesRTDB) {
        this.notes = loadedNotesRTDB;
        FirestoreNoteService.writeNoteList(loadedNotesRTDB);
    }
}