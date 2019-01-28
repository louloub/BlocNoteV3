package com.example.blocnotev3.Base;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blocnotev3.Helper.FileHelper;
import com.example.blocnotev3.MainActivity;
import com.example.blocnotev3.R;
import com.google.firebase.auth.FirebaseAuth;

public class NotesListe extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText textInput;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_liste);

        // Initialisation de l'instance "FirebaseAuth"
        mAuth = FirebaseAuth.getInstance();

        // Lien bouttons code
        textInput = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        // Création des listener
        // textInput.setOnClickListener((View.OnClickListener) this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileHelper.saveToFirebase(textInput.getText().toString());
                Toast.makeText(NotesListe.this, "Enregistré sur Firestore", Toast.LENGTH_LONG).show();
            }
        });
    }
}

