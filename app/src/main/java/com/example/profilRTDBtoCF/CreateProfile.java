package com.example.profilRTDBtoCF;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.profilRTDBtoCF.Services.FirestoreProfileService;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class CreateProfile extends AppCompatActivity {

    EditText textInput;
    EditText textInputDescription;
    Button buttonSvg;

    // Instance CloudFirebase
    private FirebaseFirestore dbcf;

    private Profile currentProfile;

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

                if (currentProfile == null) {

                    FirestoreProfileService.saveToFirebase(textInput.getText().toString(), textInputDescription.getText().toString());
                    Toast.makeText(CreateProfile.this, "Enregistré sur Firestore", Toast.LENGTH_LONG).show();
                } else {

                    /*
                    String textChange = textInput.getText().toString();
                    String textChangeDescription = textInputDescription.getText().toString();

                    currentProfile.setTitle(textChange);
                    currentProfile.setDescription(textChangeDescription);

                    FirestoreProfileService.changeToFirebase(currentProfile);

                    Toast.makeText(CreateProfile.this, "Modifié sur Firestore", Toast.LENGTH_LONG).show();
                */
                }
            }
        });

        Intent intent = getIntent();
        String title = intent.getStringExtra("note");
        String description = intent.getStringExtra("description");
        String uid = intent.getStringExtra("uid");

        if (title != null && uid != null && description != null)
        {
            // Profile profile = new Profile(uid, title, description);
            // this.currentProfile = profile;
        }

        textInput.setText(title);
        textInputDescription.setText(description);
    }
}
