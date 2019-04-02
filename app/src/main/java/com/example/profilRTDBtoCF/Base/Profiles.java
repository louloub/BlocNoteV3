package com.example.profilRTDBtoCF.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.profilRTDBtoCF.Adapter.Adapter;
import com.example.profilRTDBtoCF.CreateProfile;
import com.example.profilRTDBtoCF.Listener.ProfilesManagerListener;
import com.example.profilRTDBtoCF.Manager.ProfileManager;
import com.example.profilRTDBtoCF.Profile;
import com.example.profilRTDBtoCF.R;
import com.example.profilRTDBtoCF.Services.FirestoreProfileService;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class Profiles extends AppCompatActivity implements ProfilesManagerListener {

    private FirebaseAuth mAuth;
    private ListView listView;
    private Adapter mMAdapter;
    private TextView ViewProfil;

    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Copie des profiles de CF vers RTDB
        // "ProfileManager.get_instance()" on appel le manager
        // ProfileManager.get_instance().readCFwriteRTDB();

        listView = findViewById(R.id.listView);

        ProfileManager.get_instance().setListener(this);
        // String userId = ProfileManager.get_instance().getUserId();

        String userId = "10213328234656981" ;

        FirestoreProfileService.loadProfileCF(userId);

        // récupère la liste
        // ArrayList<Profile> profiles = ProfileManager.get_instance().getProfile();

        /*
        if (profiles.size() == 0) {
            // demande au manager de lui amener la liste de note
            ProfileManager.get_instance().loadProfile();
        }
        else {
            mMAdapter = new Adapter(Profiles.this, profiles);
            listView.setAdapter(mMAdapter);
        }
        */

        /*
        public static void getNickNameToViewProfil (String nicknameToTextView) {
            ViewProfil = findViewById(R.id.ViewProfil);
            // String profileNickname = new Profile(getNickname());
            ViewProfil.setText(nicknameToTextView);
        }
        */



        //--------
        // ADAPTER
        //--------

        // Création de l'adapteur view avec le modele XML
        if(mMAdapter != null)
        {
            listView.setAdapter(mMAdapter);
        }

        // ---------
        // Boutton Floating
        // ---------

        /*
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profiles.this, CreateProfile.class);
                startActivity(intent);
            }
        });
        */
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onProfileLoaded() {

        Profile profile = ProfileManager.get_instance().getProfile();

        /*
        // Implémentation d'un nouvel adapterListe (ArrayAdapter) "mMAdapter"
        mMAdapter = new Adapter(Profiles.this, profiles);

        // On associe l'adapter "mMAdapter "à l'adapter view "ListView" avec "setAdapter"
        listView.setAdapter(mMAdapter);
        mMAdapter.notifyDataSetChanged();
        */
    }
}