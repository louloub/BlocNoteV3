package com.example.blocnotev3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blocnotev3.Base.NotesListe;
import com.example.blocnotev3.Helper.FileHelper;

public class CreateNote extends AppCompatActivity {

    EditText textInput;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        // Lien bouttons code
        textInput = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileHelper.saveToFirebase(textInput.getText().toString());
                Toast.makeText(CreateNote.this, "Enregistré sur Firestore", Toast.LENGTH_LONG).show();
            }
        });
    }
}
