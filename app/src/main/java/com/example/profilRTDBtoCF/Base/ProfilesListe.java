package com.example.profilRTDBtoCF.Base;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import com.example.profilRTDBtoCF.Adapter.AdapterListe;
import com.example.profilRTDBtoCF.CreateNote;
import com.example.profilRTDBtoCF.Listener.ProfilesManagerListener;
import com.example.profilRTDBtoCF.Manager.ProfileManager;
import com.example.profilRTDBtoCF.Profile;
import com.example.profilRTDBtoCF.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ProfilesListe extends AppCompatActivity implements ProfilesManagerListener {

    // Firabase
    private FirebaseAuth mAuth;

    // ListView
    private ListView listView;

    private AdapterListe mMAdapterList;

    // Button Floating
    FloatingActionButton floatingActionButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_liste);

        // Copie des profiles de CF vers RTDB
        // "ProfileManager.get_instance()" on appel le manager
        // ProfileManager.get_instance().readCFwriteRTDB();

        listView = findViewById(R.id.listView);

        ProfileManager.get_instance().setListener(this);
        // récupère la liste
        ArrayList<Profile> profiles = ProfileManager.get_instance().getProfiles();

        if (profiles.size() == 0) {
            // demande au manager de lui amener la liste de note
            ProfileManager.get_instance().loadProfile();
        }
        else {
            mMAdapterList = new AdapterListe(ProfilesListe.this, profiles);
            listView.setAdapter(mMAdapterList);
        }

        //--------
        // ADAPTER
        //--------

        // Création de l'adapteur view (list view) avec le modele XML
        if(mMAdapterList != null)
        {
            listView.setAdapter(mMAdapterList);
        }

        // ---------
        // Boutton Floating
        // ---------

        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilesListe.this, CreateNote.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onProfileListLoaded() {

        ArrayList<Profile> profiles = ProfileManager.get_instance().getProfiles();
        // Implémentation d'un nouvel adapterListe (ArrayAdapter) "mMAdapterList"
        mMAdapterList = new AdapterListe(ProfilesListe.this, profiles);

        // On associe l'adapter "mMAdapterList "à l'adapter view "ListView" avec "setAdapter"
        listView.setAdapter(mMAdapterList);

        mMAdapterList.notifyDataSetChanged();

    }
}