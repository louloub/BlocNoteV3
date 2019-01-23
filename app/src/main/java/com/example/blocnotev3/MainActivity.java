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

import com.google.firebase.firestore.FirebaseFirestore;

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
            }

            // if (FileHelper.saveToFirebase(textInput.getText().toString())){
            //     Toast.makeText(MainActivity.this, "Saved to Firestore", Toast.LENGTH_SHORT).show();
            // }else{
            //     Toast.makeText(MainActivity.this, "Error to save", Toast.LENGTH_SHORT).show();
            // }
                // textToSend = textInput;
                // EditText textToSend = null;
                // textToSend.setText("s");

        });

    }

    @Override
    public void onClick(View v) {

    }
}
// textInput.addTextChanged

// ---------------------
// TEXT TO FIREBASE
// ---------------------

// EditText textToSend = null;
// textToSend.setText("s");

// convertir le "CharSequence" (s) en "String" (ss) pour l'objet "Note"
// String ss = s.toString();
// Modifier "Message" dans "Note" avec "ss"
// db.collection("notes").add(ss);

// ---------------------
// CODE CHOPPE EN LIGNE
// ---------------------
// btnSave = (Button) findViewById(R.id.btnSave);
//         btnSave.setOnClickListener(new View.OnClickListener() {
// @Override
// public void onClick(View v) {
//         if (FileHelper.saveToFile( txtInput.getText().toString())){
//         Toast.makeText(MainActivity.this,"Saved to file",Toast.LENGTH_SHORT).show();
//         }else{
//         Toast.makeText(MainActivity.this,"Error save file!!!",Toast.LENGTH_SHORT).show();
//         }
//         }
//        });

// ---------------------
// METHODE ADDTEXTCHANGEDLISTENTER
// ---------------------

/* textInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // textInput.setText(s);
                // String textToSend = s.toString();

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        */