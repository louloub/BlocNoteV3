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

public class CreateNote extends AppCompatActivity {

    EditText textInput;
    EditText textInputDescription;
    Button buttonSvg;

    // Instance CloudFirebase
    private FirebaseFirestore dbcf;

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";
    private Note currentNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        dbcf = FirebaseFirestore.getInstance();

        // Lien bouttons / code
        textInput = findViewById(R.id.editText);
        textInputDescription = findViewById(R.id.editTextDescription);
        buttonSvg = findViewById(R.id.buttonSvg);

        buttonSvg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> note = new HashMap<>();

                if (currentNote == null) {

                    saveToFirebase(textInput.getText().toString(), textInputDescription.getText().toString());
                    Toast.makeText(CreateNote.this, "Enregistré sur Firestore", Toast.LENGTH_LONG).show();
                } else {

                    String textChange = textInput.getText().toString();
                    String textChangeDescription = textInputDescription.getText().toString();

                    currentNote.setTitle(textChange);
                    currentNote.setDescription(textChangeDescription);

                    changeToFirebase(currentNote);

                    Toast.makeText(CreateNote.this, "Modifié sur Firestore", Toast.LENGTH_LONG).show();
                }
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("note");
        String description = intent.getStringExtra("description");
        String uid = intent.getStringExtra("uid");

        if (title != null && uid != null && description != null)
        {
            Note note = new Note(uid, title, description);
            this.currentNote = note;
        }

        textInput.setText(title);
        textInputDescription.setText(description);

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
        // noteMap.put("description", noteDescriptionToUpdate);

        return FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION)
                .document(DOCUMENT_NAME)
                .collection(NOTE_COLLECTION)
                .document(noteToUpdate.getUid())
                .set(noteMap, SetOptions.merge());

    }
}
