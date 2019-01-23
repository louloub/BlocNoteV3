package com.example.blocnotev3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Création des bouttons
    EditText textInput;
    Button button;

    // FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
    public void onClick(View v) {

    }
}