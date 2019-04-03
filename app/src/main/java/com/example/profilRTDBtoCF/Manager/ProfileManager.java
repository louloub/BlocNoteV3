package com.example.profilRTDBtoCF.Manager;

import android.util.Log;

import com.example.profilRTDBtoCF.Base.Profiles;
import com.example.profilRTDBtoCF.Listener.CloudFirestoreServiceListener;
import com.example.profilRTDBtoCF.Listener.DataBaseServiceListener;
import com.example.profilRTDBtoCF.Listener.ProfilesManagerListener;
import com.example.profilRTDBtoCF.Profile;
import com.example.profilRTDBtoCF.Services.DataBaseProfileService;
import com.example.profilRTDBtoCF.Services.FirestoreProfileService;


import static android.app.PendingIntent.getActivity;
import static com.facebook.login.widget.ProfilePictureView.TAG;

public class ProfileManager implements DataBaseServiceListener, CloudFirestoreServiceListener {

    private static ProfileManager _instance;
    private Profile profile ;

    private static String userId = "10213328234656981";

    // Déclaration du "listener" de "ProfilesManagerListener"
    private ProfilesManagerListener listener;

    public synchronized static ProfileManager get_instance() {
        if (_instance == null) {
            _instance = new ProfileManager();
        }
        return _instance;
    }

    // -----------------------
    // Constructeur du manager
    // -----------------------

    private ProfileManager() {
        // définir les listener
        FirestoreProfileService.setListenerCF(this);
        DataBaseProfileService.setListenerRTDB(this);
        Log.d(TAG, "test définition listener");

    }

    // -----------------------
    // GETTER SETTER
    // -----------------------

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public static String getUserId() {
        return userId;
    }

    public Profile getProfile() {
        return profile;
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
    /*
    public void profileLoaded() {

        if(listener != null)
        {
            listener.onProfileLoaded();
        }
    }
    */

    //---------
    // LOAD CF
    //---------

    @Override
    public void onProfileLoadedFromCF(Profile profile) {
        this.profile = profile;
        // listenerCF.onProfileLoadedFromCF(loadedProfilesCF);
    }

    @Override
    public void onProfileNotFoundInCF() {
        DataBaseProfileService.readProfileRTDB();
    }

    @Override
    public void onProfileWriteInCF(Profile profile) {
        Log.d(TAG, "onProfileWriteInCF");
        // Profiles.writeProfileOnTextView();
        listener.onProfileLoaded(profile);
        // FirestoreProfileService.writeProfileOnTextView(profile);

    }

    //---------
    // LOAD RTDB
    //---------

    @Override
    public void onProfileLoadedFromRTDB(Profile profile) {
        // this.profile = loadedProfilesRTDB;
        FirestoreProfileService.writeProfileCF(profile);
    }
}