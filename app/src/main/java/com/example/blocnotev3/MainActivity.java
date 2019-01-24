package com.example.blocnotev3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blocnotev3.Helper.FileHelper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Création des bouttons
    EditText textInput;
    Button button;

    // Déclaration FirebaseAuth
    private FirebaseAuth mAuth;

    // FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de l'instance "FirebaseAuth"
        mAuth = FirebaseAuth.getInstance();

        // Lien bouttons code
        textInput = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        // Création des listener
        textInput.setOnClickListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FileHelper.saveToFirebase(textInput.getText().toString());
                Toast.makeText(MainActivity.this, "Enregistré sur Firestore", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }


    @Override
    public void onClick(View v) {

    }
}

/*

-----------
-- onCreate
-----------
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de l'instance "FirebaseAuth"
        mAuth = FirebaseAuth.getInstance();

        // Lien bouttons code
        textInput = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        // Création des listener
        textInput.setOnClickListener(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FileHelper.saveToFirebase(textInput.getText().toString());
                Toast.makeText(MainActivity.this, "Enregistré sur Firestore", Toast.LENGTH_LONG).show();
            }
        });
    }


 */