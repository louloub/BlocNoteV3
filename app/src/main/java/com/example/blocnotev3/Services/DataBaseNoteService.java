package com.example.blocnotev3.Services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.blocnotev3.Helper.Helper;
import com.example.blocnotev3.Manager.NotesManager;
import com.example.blocnotev3.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class DataBaseNoteService {

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";

    public static  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public static void readCFwriteRTDB() {

        // FirestoreNoteService.loadNoteList();

        //----------
        // READ CF
        //----------

        CollectionReference chatDocumentNotes = Helper.getChatDocumentNotes();

        chatDocumentNotes.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            // Override : On s'approprie la methode "onComplete" qui est étendue de "AppCompatActivity"
            // onComplete : Called when the Task completes.
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    // Parcourir tous les documents et donner les resultats (getResult)
                    for (QueryDocumentSnapshot document : task.getResult())
                    {
                        String title = document.getString("first");
                        String description = document.getString("description");
                        String uid = document.getId();

                        Note note = new Note(uid, title, description );

                        NotesManager.get_instance().getNotes().add(note);

                        //----------
                        // WRITE RTDB
                        //----------

                        /*
                        Map<String, Object> noteHashMap = note.toMap();

                        DatabaseReference noteRef = databaseReference.child(uid);

                        noteRef.setValue(noteHashMap);
                        */

                        // databaseReference.child("notes").setValue("test1","test2");
                        // databaseReference.child("notes").setValue(note.toMap());
                        // databaseReference.child("notes").setValue(note);
                        // databaseReference.child(uid).setValue(title, description);
                        databaseReference.child(uid).setValue(note.toMap());
                    }

                    }

                    // On appelle le singleton en utilisant le "get instance"
                    NotesManager.get_instance().listLoaded();

                }
        });
    }
}