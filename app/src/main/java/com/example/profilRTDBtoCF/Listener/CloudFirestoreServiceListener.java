package com.example.profilRTDBtoCF.Listener;

import com.example.profilRTDBtoCF.Profile;

import java.util.ArrayList;

public interface CloudFirestoreServiceListener {
    void onProfileLoadedFromCF(Profile profile);
    void onProfileNotFoundInCF();
}

