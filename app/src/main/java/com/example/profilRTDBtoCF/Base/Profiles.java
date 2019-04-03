package com.example.profilRTDBtoCF.Base;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.profilRTDBtoCF.Adapter.Adapter;
import com.example.profilRTDBtoCF.Listener.ProfilesManagerListener;
import com.example.profilRTDBtoCF.Manager.ProfileManager;
import com.example.profilRTDBtoCF.Profile;
import com.example.profilRTDBtoCF.R;
import com.example.profilRTDBtoCF.Services.FirestoreProfileService;
import com.google.firebase.auth.FirebaseAuth;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class Profiles extends AppCompatActivity implements ProfilesManagerListener {

    private FirebaseAuth mAuth;
    private ListView listView;
    private Adapter mMAdapter;
    private TextView ViewProfil;

    FloatingActionButton floatingActionButton;

    /*
    public static String writeProfileOnTextView() {
        String nicknameToTextView = Profile.getNickname();
        Log.d(TAG, "writeProfileOnTextView" + nicknameToTextView);
        return nicknameToTextView ;
    }
    */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        listView = findViewById(R.id.listView);

        // La vue écoute le "ProfileManager"
        ProfileManager.get_instance().setListener(this);

        String userId = "10213328234656981";

        FirestoreProfileService.loadProfileCF(userId);

        /*
        public static void getNickNameToViewProfil (String nicknameToTextView) {
            ViewProfil = findViewById(R.id.ViewProfil);
            // String profileNickname = new Profile(getNickname());
            ViewProfil.setText(nicknameToTextView);
        }
        */

            ViewProfil = findViewById(R.id.ViewProfil);
            // ViewProfil.setText();

            //--------
            // ADAPTER
            //--------

            // Création de l'adapteur view avec le modele XML
            if (mMAdapter != null) {
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
        protected void onResume () {
            super.onResume();
        }

        @Override
        public void onProfileLoaded(Profile profile) {
            String nicknameToTextView = Profile.getNickname();
            Log.d(TAG, "writeProfileOnTextView" + nicknameToTextView);
            ViewProfil.setText(nicknameToTextView);

        }
    }