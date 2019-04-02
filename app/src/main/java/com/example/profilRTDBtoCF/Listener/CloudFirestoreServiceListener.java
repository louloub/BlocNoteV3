package com.example.profilRTDBtoCF.Listener;

import com.example.profilRTDBtoCF.Profile;

public interface CloudFirestoreServiceListener {
    void onProfileLoadedFromCF(Profile profile);
    void onProfileNotFoundInCF();
    void onProfileWriteInCF(Profile profile);
}

