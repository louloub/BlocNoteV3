package com.example.profilRTDBtoCF.Base;

import android.os.Bundle;
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

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class Profiles extends AppCompatActivity implements ProfilesManagerListener {

    private ListView listView;
    private Adapter mMAdapter;
    private TextView ViewProfil;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        listView = findViewById(R.id.listView);

        // La vue écoute le "ProfileManager"
        ProfileManager.get_instance().setListener(this);

        FirestoreProfileService.loadProfileCF(ProfileManager.getUserId());

        ViewProfil = findViewById(R.id.ViewProfil);

        //----------------
        // ADAPTER
        //----------------

            // Création de l'adapteur view avec le modele XML
            if (mMAdapter != null) {
                listView.setAdapter(mMAdapter);
            }
        }

        @Override
        protected void onResume () {
            super.onResume();
        }

        @Override
        public void onProfileLoaded(Profile profile) {
            String nicknameToTextView = Profile.getNickname();
            ViewProfil.setText(nicknameToTextView);
        }
}