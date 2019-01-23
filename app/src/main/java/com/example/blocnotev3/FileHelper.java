package com.example.blocnotev3;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;

import java.util.HashMap;
import java.util.Map;

public class FileHelper {
    final static String TAG = FileHelper.class.getName();
    private static final String COLLECTION_NAME = "notes";

    public static Task<DocumentReference> saveToFirebase(String textMessage){

        Map<String, Object> user = new HashMap<>();
        user.put("first", textMessage);

        return Helper.getChatCollection()
                .document("document")
                .collection(COLLECTION_NAME)
                .add(user);
    }
}
