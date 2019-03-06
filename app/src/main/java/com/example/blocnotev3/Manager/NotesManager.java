package com.example.blocnotev3.Manager;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ListView;

import com.example.blocnotev3.Adapter.AdapterListe;
import com.example.blocnotev3.Base.NotesListe;
import com.example.blocnotev3.Helper.Helper;
import com.example.blocnotev3.Listener.NotesListener;
import com.example.blocnotev3.Note;
import com.example.blocnotev3.R;
import com.example.blocnotev3.Services.DataBaseNoteService;
import com.example.blocnotev3.Services.FirestoreNoteService;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class NotesManager {

    private static NotesManager _instance;
    private ArrayList<Note> notes = new ArrayList<>();

    private NotesListener listener;

    public synchronized static NotesManager get_instance() {
        if (_instance == null) {
            _instance = new NotesManager();
        }
        return _instance;
    }

    private NotesManager() {
        //
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setListener(NotesListener listener) {
        this.listener = listener;
    }

    public void loadNoteList() {

        FirestoreNoteService.loadNoteList();
    }

    // On s'assure que la liste est bien loaded
    public void listLoaded() {

        if(listener != null)
        {
            listener.onNoteListLoaded();
        }
    }

    public void readCFwriteRTDB(){
        DataBaseNoteService.readCFwriteRTDB();
    }
}
