package com.example.profilRTDBtoCF;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.profilRTDBtoCF.Base.ProfilesListe;
import com.example.profilRTDBtoCF.Helper.Helper;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_SIGN_IN = 123;

    // Déclaration FirebaseAuth
    private FirebaseAuth mAuth;
    private CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialisation de l'instance "FirebaseAuth"
        mAuth = FirebaseAuth.getInstance();

        Button button = findViewById(R.id.main_activity_button_login);
        // On ajoute le paramétre "button" à la méthode appelée "updateUIWhenResuming"
        this.updateUIWhenResuming(button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Si l'utilisateur est connecté, alors on passe sur l'activité suivante
                // Start appropriate activity

                if (this.isCurrentUserLogged())
                {
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

            // Launching "NotesListeActivity"
            private void startNotesListeActivity() {
                Intent intent = new Intent(MainActivity.this, ProfilesListe.class);
                startActivity(intent);
            }
        });
    }

    // Update UI when activity is resuming : mise à jour du texte du "button" en fonction du statut de l'utilisateur
    private void updateUIWhenResuming(Button button) {
        button.setText
                (this.isCurrentUserLogged() ? getString(R.string.button_login_text_logged) : getString(R.string.button_login_text_not_logged));
    }

    // L'utilisateur actuel est il connecté ?
    protected Boolean isCurrentUserLogged() {
        return (this.getCurrentUser() !=null);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle (manipuler) SignIn Activity response on activity result
        this.handleResponseAfterSignIn(requestCode, resultCode, data);
    }

    // Method that handles (manipuler) response after SignIn Activity close
    private void handleResponseAfterSignIn(int requestCode, int resultCode, Intent data) {

        IdpResponse response = IdpResponse.fromResultIntent(data);

        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) { // SUCCESS
                this.createUserInFirestore();
                showSnackBar(this.coordinatorLayout, getString(R.string.connection_succeed));
            } else { // ERRORS
                if (response == null) {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_authentication_canceled));
                } else if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_no_internet));
                } else if (response.getError().getErrorCode() == ErrorCodes.UNKNOWN_ERROR) {
                    showSnackBar(this.coordinatorLayout, getString(R.string.error_unknown_error));
                }
            }
        }
    }

    // Show Snack Bar with a message
    private void showSnackBar(CoordinatorLayout coordinatorLayout, String message) {
        Snackbar.make((findViewById(R.id.main_activity_coordinator_layout)), message, Snackbar.LENGTH_SHORT).show();
    }

    // Http request that create user in firestore
    private void createUserInFirestore(){
        if (this.getCurrentUser() != null){
            String urlPicture = (this.getCurrentUser().getPhotoUrl() != null ? this.getCurrentUser().getPhotoUrl().toString() : null);
            String username = this.getCurrentUser().getDisplayName();
            String uid = this.getCurrentUser().getUid();

            Helper.createUser(uid, username, urlPicture).addOnFailureListener(this.onFailureListener());
        }
    }

    protected OnFailureListener onFailureListener(){
        return new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), getString(R.string.error_unknown_error), Toast.LENGTH_LONG).show();
            }
        };
    }

    // Récupère l'utilisateur actuellement connecté à l'app
    protected FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public void onClick(View v) {

    }
}