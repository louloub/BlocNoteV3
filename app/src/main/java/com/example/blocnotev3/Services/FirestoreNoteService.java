package com.example.blocnotev3.Services;

import android.support.annotation.NonNull;

import com.example.blocnotev3.Helper.Helper;
import com.example.blocnotev3.Listener.CloudFirestoreServiceListener;
import com.example.blocnotev3.Manager.NotesManager;
import com.example.blocnotev3.Note;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FirestoreNoteService {

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";

    private static CloudFirestoreServiceListener listenerCF;

    public static void setListenerCF(CloudFirestoreServiceListener listenerCF) {
        FirestoreNoteService.listenerCF = listenerCF;
    }

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

        CollectionReference notesReference = FirebaseFirestore.getInstance().collection(NOTE_COLLECTION);

        notesReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            // Override : On s'approprie la methode "onComplete" qui est étendue de "AppCompatActivity"
            // onComplete : Called when the Task completes.
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                    if (task.isSuccessful()) {

                        if (task.getResult().size() >0){

                        ArrayList<Note> listNote = new ArrayList<>();

                        // Parcourir tous les documents et donner les resultats (getResult)
                        for (QueryDocumentSnapshot document : task.getResult())
                        {
                            String title = document.getString("first");
                            String description = document.getString("description");
                            String uid = document.getId();

                            Note note = new Note(uid, title, description );

                            listNote.add(note);

                            // On appelle le singleton en utilisant le "get instance"
                            // NotesManager.get_instance().getNotes().add(note);
                        }

                        listenerCF.onNoteListeLoadedFromCF(listNote);

                    } else {
                            listenerCF.onNoteListNotFoundInCF();

                    }
                }
            }
        });
    }

    // Ecrire list de note dans CF
    public static void writeNoteList(ArrayList<Note> noteListToWrite){

        // Boucle FOR pour parcourir la liste des notes passée en paramètres
        for (Note note : noteListToWrite) {
            // Pour chaque note je construis une hashmap pour écrire en BDD
            Map<String, Object> noteMap = note.toMap();
            // Je créé ma référence
            CollectionReference notesReference = FirebaseFirestore.getInstance().collection(NOTE_COLLECTION);
            // J'écris la hasmap avec ma référence
            notesReference.add(noteMap);

        }
    }
}