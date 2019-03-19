package com.example.blocnotev3.Helper;

import android.view.View;
import android.widget.Button;

import com.example.blocnotev3.R;
import com.example.blocnotev3.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Helper implements View.OnClickListener{
    // Création des noms de collections & documents
    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";

    // --- COLLECTION REFERENCE ---
    public static CollectionReference getChatCollection(){
        return FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION);

    }

    // --- GET CHAT DOCUMENT NOTES DATA ---
    public static CollectionReference getChatDocumentNotes (){
        return FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION)
                .document(DOCUMENT_NAME)
                .collection(NOTE_COLLECTION);
    }

    public static CollectionReference getNotes (){
        return FirebaseFirestore.getInstance()
                .collection(NOTE_COLLECTION);
    }

    // --- CREATE ---
    public static Task<Void> createUser(String uid, String username, String urlPicture) {
        User userToCreate = new User(uid, username, urlPicture);
        return Helper.getChatCollection()
                .document(uid)
                .set(userToCreate);
    }

    // --- GET ---
    // Reads the document referenced by this DocumentReference.
    public static Task<DocumentSnapshot> getUser(String uid) {
        return Helper.getChatCollection()
                .document(uid)
                .get();
    }

    // --- UPDATE ---
    // Update du nom d'utilisateur
    public static Task<Void> updateUsername(String username, String uid) {
        return Helper.getChatCollection()
                .document(uid)
                .update("username", username);
    }

    // Update du boolean "mentor"
    public static Task<Void> updateIsMentor(String uid, Boolean isMentor) {
        return Helper.getChatCollection()
                .document(uid)
                .update("isMentor", isMentor);
    }

    // --- DELETE ---
    // Suppression de l'utilisateur
    public static Task<Void> deleteUser(String uid) {
        return Helper.getChatCollection()
                .document(uid)
                .delete();
    }

    @Override
    public void onClick(View v) {

    }
}
