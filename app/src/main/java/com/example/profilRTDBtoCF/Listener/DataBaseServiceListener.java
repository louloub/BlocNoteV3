package com.example.profilRTDBtoCF.Listener;

import com.example.profilRTDBtoCF.Profile;

import java.util.ArrayList;

public interface DataBaseServiceListener {
    void onProfileLoadedFromRTDB(Profile profile);
}
