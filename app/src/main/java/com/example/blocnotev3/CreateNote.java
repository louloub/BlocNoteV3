package com.example.blocnotev3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blocnotev3.Helper.FileHelper;
import com.example.blocnotev3.Helper.Helper;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

public class CreateNote extends AppCompatActivity {

    EditText textInput;
    Button buttonSvg;

    // Instance CloudFirebase
    private FirebaseFirestore dbcf;

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";

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

                /*
                Helper.getChatCollection()
                        .document(DOCUMENT_NAME)
                        .collection(NOTE_COLLECTION)
                        .document(note.uid)
                        .set(Note.title, SetOptions.merge());
                */

                // FileHelper.saveToFirebase();

                FileHelper.saveToFirebase((Note) textInput.getText());


                Toast.makeText(CreateNote.this, "Enregistré sur Firestore", Toast.LENGTH_LONG).show();
                // changeNoteCF(str);
            }
        });

        Note note = new Note();
        Intent intent = new Intent(this, FileHelper.class);
        intent.putExtra("note", (Note) textInput.getText());
        startActivity(intent);

        /*
        Intent intent = getIntent();
        String str = intent.getStringExtra("item");
        textInput.setText(str);
         */

        // Note noteToUpdate = (Note) intent.getSerializableExtra("item");
        // textInput.setText(noteToUpdate);

        // changeNoteCF(str);
        // button.changeNoteCF(String str);
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
