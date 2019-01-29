package com.example.blocnotev3.Helper;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

public class FileHelper {
    private static final String COLLECTION_NAME = "notes";

    public static Task<DocumentReference> saveToFirebase(String textMessage){

        Map<String, Object> user = new HashMap<>();
        // "first" est la clé qui permet de chercher "textMessage" dans la "HashMap"
        user.put("first", textMessage);

        return Helper.getChatCollection()
                .document("document")
                .collection(COLLECTION_NAME)
                .add(user)
                ;
    }
}

/*
return Helper.getChatCollection()
                .document("document")
                .collection(COLLECTION_NAME)
                .add(user);
                .collection("notes")
                .orderBy("", "asc")

 */
