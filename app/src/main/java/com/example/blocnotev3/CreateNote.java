package com.example.blocnotev3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static com.example.blocnotev3.Note.uid;

public class CreateNote extends AppCompatActivity {

    EditText textInput;
    Button buttonSvg;

    // Instance CloudFirebase
    private FirebaseFirestore dbcf;

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";
    private String currentUID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        dbcf = FirebaseFirestore.getInstance();

        // Lien bouttons / code
        textInput = findViewById(R.id.editText);
        buttonSvg = findViewById(R.id.buttonSvg);

        buttonSvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> note = new HashMap<>();

                if (currentUID == null) {
                    saveToFirebase(note);
                    Toast.makeText(CreateNote.this, "Enregistré sur Firestore", Toast.LENGTH_LONG).show();
                } else {
                    changeToFirebase(note);
                    Toast.makeText(CreateNote.this, "Modifié sur Firestore", Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        Note note = new Note();
        Intent intent = new Intent(this, FileHelper.class);
        intent.putExtra("note", textInput.getText());
        startActivity(intent);

        Intent intent = new Intent();
        Note noteIntent = (Note)intent.getSerializableExtra("note");
        */

        Intent intent = getIntent();
        String title = intent.getStringExtra("note");
        String uid = intent.getStringExtra("uid");

        Note note = new Note(uid, title);

        this.currentUID = uid;

        textInput.setText(title);
    }

    // Methode qui sauvergarde les données en BDD
    public static Task<DocumentReference> saveToFirebase(Map<String, Object> note) {
        Map<String, Object> noteMap = new HashMap<>();

        return FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION)
                .document(DOCUMENT_NAME)
                .collection(NOTE_COLLECTION)
                .add(noteMap);

        // Map<String, Object> note = new HashMap<>();
        // "first" est la clé qui permet de chercher "textMessage" dans la "HashMap"
        // note.put("first", textMessage);
        // note.put("uid", uid);
    }

    public static Task<Void> changeToFirebase(Map<String, Object> note) {
        Map<String, Object> noteMap = new HashMap<>();

        return FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION)
                .document(DOCUMENT_NAME)
                .collection(NOTE_COLLECTION)
                .document("uid")
                .set(noteMap, SetOptions.merge());


        // Map<String, Object> note = new HashMap<>();
        // "first" est la clé qui permet de chercher "textMessage" dans la "HashMap"
        // note.put("first", textMessage);
        // note.put("uid", uid);
    }

    // --------------------
    // CHANGED CF        |
    // --------------------

    /*
    public void changeNoteCF(String str){
        dbcf.collection(CHAT_COLLECTION)
                .document(DOCUMENT_NAME)
                .collection(NOTE_COLLECTION);
                // .document(Note.uid)
                // .set(str, SetOptions.merge());

    }
    */
}
