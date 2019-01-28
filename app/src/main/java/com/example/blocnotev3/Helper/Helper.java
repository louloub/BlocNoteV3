package com.example.blocnotev3.Helper;

import android.annotation.SuppressLint;

import com.example.blocnotev3.User;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Helper {
    private static final String COLLECTION_NAME = "chats";

    // --- COLLECTION REFERENCE ---

    public static CollectionReference getChatCollection(){
        return FirebaseFirestore.getInstance()
                .collection(COLLECTION_NAME);

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
}
