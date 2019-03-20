package com.example.blocnotev3.Services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.blocnotev3.Helper.Helper;
import com.example.blocnotev3.Listener.DataBaseServiceListener;
import com.example.blocnotev3.Manager.NotesManager;
import com.example.blocnotev3.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class DataBaseNoteService {

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";

    public static  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private static DataBaseServiceListener listenerRTDB;

    public static void setListenerRTDB(DataBaseServiceListener listenerRTDB) {
        DataBaseNoteService.listenerRTDB = listenerRTDB;
    }

    public static void readCFwriteRTDB() {

        // FirestoreNoteService.loadNoteList();

        //----------
        // READ CF
        //----------

        CollectionReference notesReference = Helper.getNotes();

        notesReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            // Override : On s'approprie la methode "onComplete" qui est étendue de "AppCompatActivity"
            // onComplete : Called when the Task completes.
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    ArrayList<Note> listNote = new ArrayList<>();

                    // Parcourir tous les documents et donner les resultats (getResult)
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        String title = document.getString("first");
                        String description = document.getString("description");
                        String uid = document.getId();

                        Note note = new Note(uid, title, description );

                        listNote.add(note);

                        // NotesManager.get_instance().getNotes().add(note);

                        //----------
                        // WRITE RTDB
                        //----------
                        databaseReference.child(uid).setValue(note.toMap());

                        /*
                        Map<String, Object> noteHashMap = note.toMap();

                        DatabaseReference noteRef = databaseReference.child(uid);

                        noteRef.setValue(noteHashMap);
                        */
                    }
                    listenerRTDB.onNoteListeLoadedFromRTDB(listNote);

                    }

                    // On appelle le singleton en utilisant le "get instance"
                    // NotesManager.get_instance().listLoaded();

                }
        });
    }

    public static void readNoteRTDB() {

        ValueEventListener noteListListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {

                    ArrayList<Note> listNote = new ArrayList<>();

                    // parcourir les snapshot des notes
                    for(DataSnapshot noteSnap : dataSnapshot.getChildren()) {

                        String uid = noteSnap.getKey();
                        String title = noteSnap.child("title").getValue(String.class);
                        String description = noteSnap.child("description").getValue(String.class);

                        Note note = new Note(uid,title,description);

                        listNote.add(note);
                    }

                    listenerRTDB.onNoteListeLoadedFromRTDB(listNote);


                }
                else
                {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        databaseReference.addValueEventListener(noteListListener);
    }


}