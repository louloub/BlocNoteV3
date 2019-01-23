package com.example.blocnotev3;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class  MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Création des bouttons
    EditText editText;
    Button button;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Lien bouttons code
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        // Création des listener
        editText.setOnClickListener(this);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editText.setText(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // textToSend = editText;
                // EditText textToSend = null;
                // textToSend.setText("s");
            }
        });

    }

    @Override
    public void onClick(View v) {

    }
}
// editText.addTextChanged

// ---------------------
// TEXT TO FIREBASE
// ---------------------

// EditText textToSend = null;
// textToSend.setText("s");

// convertir le "CharSequence" (s) en "String" (ss) pour l'objet "Note"
// String ss = s.toString();
// Modifier "Message" dans "Note" avec "ss"
// db.collection("notes").add(ss);