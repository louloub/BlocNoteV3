package com.example.blocnotev3.Services;

import android.support.annotation.NonNull;

import com.example.blocnotev3.Helper.Helper;
import com.example.blocnotev3.Manager.NotesManager;
import com.example.blocnotev3.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class FirestoreNoteService {

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";

    // Methode qui sauvergarde les données en BDD
    public static Task<DocumentReference> saveToFirebase(String noteText, String noteDescription) {

        Map<String, Object> noteMap = new HashMap<>();
        noteMap.put("first", noteText);
        noteMap.put("description", noteDescription);

        return FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION)
                .document(DOCUMENT_NAME)
                .collection(NOTE_COLLECTION)
                .add(noteMap);
    }

    public static Task<Void> changeToFirebase(Note noteToUpdate) {

        Map<String, Object> noteMap = new HashMap<>();
        noteMap.put("first", noteToUpdate.getTitle());
        noteMap.put("description", noteToUpdate.getDescription());

        return FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION)
                .document(DOCUMENT_NAME)
                .collection(NOTE_COLLECTION)
                .document(noteToUpdate.getUid())
                .set(noteMap, SetOptions.merge());
    }

    public static void loadNoteList() {

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
                    }

                    // On appelle le singleton en utilisant le "get instance"
                    NotesManager.get_instance().listLoaded();

                } else {
                }
            }
        });
    }
}