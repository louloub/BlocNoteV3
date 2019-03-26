package com.example.profilRTDBtoCF.Manager;

import android.util.Log;

import com.example.profilRTDBtoCF.Listener.CloudFirestoreServiceListener;
import com.example.profilRTDBtoCF.Listener.DataBaseServiceListener;
import com.example.profilRTDBtoCF.Listener.ProfilesManagerListener;
import com.example.profilRTDBtoCF.Profile;
import com.example.profilRTDBtoCF.Services.DataBaseProfileService;
import com.example.profilRTDBtoCF.Services.FirestoreProfileService;


import java.util.ArrayList;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class ProfileManager implements DataBaseServiceListener, CloudFirestoreServiceListener {

    private static ProfileManager _instance;
    private ArrayList<Profile> profiles = new ArrayList<>();

    private String userId = "10213328234656981";

    // Déclaration du "listener" de "ProfilesManagerListener"
    private ProfilesManagerListener listener;

    public synchronized static ProfileManager get_instance() {
        if (_instance == null) {
            _instance = new ProfileManager();
        }
        return _instance;
    }

    // Constructeur du manager
    private ProfileManager() {
        // définir les listener
        FirestoreProfileService.setListenerCF(this);
        DataBaseProfileService.setListenerRTDB(this);
        Log.w(TAG, "définition listener");

    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }

    public void setListener(ProfilesManagerListener listener) {
        this.listener = listener;
    }

    //---------
    // READ
    //---------

    public void readCFwriteRTDB(){
        DataBaseProfileService.readCFwriteRTDB();
    }

    //---------
    // LOAD
    //---------

    public void loadProfile() {
        FirestoreProfileService.loadProfileCF(userId);
    }

    // On s'assure que la liste est bien loaded avec un LISTENER
    public void listLoaded() {

        if(listener != null)
        {
            listener.onProfileListLoaded();
        }
    }

    //---------
    // LOAD CF
    //---------

    @Override
    public void onProfilesLoadedFromCF(ArrayList<Profile> loadedProfilesCF) {
        this.profiles = loadedProfilesCF;
        // listenerCF.onProfilesLoadedFromCF(loadedProfilesCF);
    }

    @Override
    public void onProfileNotFoundInCF() {
        DataBaseProfileService.readProfileRTDB(userId);
    }

    //---------
    // LOAD RTDB
    //---------

    @Override
    public void onProfileLoadedFromRTDB(ArrayList<Profile> loadedProfilesRTDB) {
        this.profiles = loadedProfilesRTDB;
        FirestoreProfileService.writeProfileList(loadedProfilesRTDB);
    }
}