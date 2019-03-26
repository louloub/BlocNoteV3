package com.example.profilRTDBtoCF.Listener;

import com.example.profilRTDBtoCF.Profile;

import java.util.ArrayList;

public interface CloudFirestoreServiceListener {
    void onProfilesLoadedFromCF(ArrayList<Profile> loadedNotesCF);
    void onProfileNotFoundInCF();
}

