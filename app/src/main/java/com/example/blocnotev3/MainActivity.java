package com.example.blocnotev3;

import android.content.ContentProvider;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blocnotev3.Auth.ProfilActivity;
import com.example.blocnotev3.Base.NotesListe;
import com.example.blocnotev3.Helper.FileHelper;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private static final int RC_SIGN_IN = 123;

    // Déclaration FirebaseAuth
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de l'instance "FirebaseAuth"
        mAuth = FirebaseAuth.getInstance();

        Button button = findViewById(R.id.main_activity_button_login);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si l'utilisateur est connecté, alors on passe sur l'activité suivante
                // Start appropriate activity
                if (this.isCurrentUserLogged()) {
                    this.startNotesListeActivity();
                } else {
                    this.startSignInActivity();
                }

            }

            // L'utilisateur actuel est il connecté ?
            protected Boolean isCurrentUserLogged() {
                return (this.getCurrentUser() !=null);
            }

            // Récupère l'utilisateur actuellement connecté à l'app
            protected FirebaseUser getCurrentUser() {
                return FirebaseAuth.getInstance().getCurrentUser();
            }

            // Launching Profile Activity
            private void startNotesListeActivity() {
                Intent intent = new Intent(MainActivity.this, NotesListe.class);
                startActivity(intent);
            }

            // Launch Sign-In Activity
            private void startSignInActivity() {
                startActivityForResult(
                        AuthUI.getInstance()
                                // Création d'une activité de connexion géré par firebase UI
                                .createSignInIntentBuilder()
                                .setTheme(R.style.LoginTheme) // Personnalisation de l'écran
                                // Ajout des moyens d'authentification
                                // Methode auth par email
                                .setAvailableProviders(
                                        Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(), // EMAIL
                                                new AuthUI.IdpConfig.FacebookBuilder().build(), // FACEBOOK
                                                new AuthUI.IdpConfig.GoogleBuilder().build())) // GOOGLE
                                .setIsSmartLockEnabled(false, true)
                                .setLogo(R.drawable.ic_logo_auth)
                                .build(),
                        RC_SIGN_IN);
            }

        });

    }
/*
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
    }
*/
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