package com.example.profilRTDBtoCF.Services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.profilRTDBtoCF.Gender;
import com.example.profilRTDBtoCF.Listener.CloudFirestoreServiceListener;
import com.example.profilRTDBtoCF.Manager.ProfileManager;
import com.example.profilRTDBtoCF.Profile;
import com.example.profilRTDBtoCF.ProfileStatus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class FirestoreProfileService {

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String PROFILE_COLLECTION = "profiles";

    private static CloudFirestoreServiceListener listenerCF;

    public static void setListenerCF(CloudFirestoreServiceListener listenerCF) {
        FirestoreProfileService.listenerCF = listenerCF;
    }

    // Methode qui sauvergarde les données en BDD
    public static Task<DocumentReference> saveToFirebase(String profilText, String profileDescription) {

        Map<String, Object> profileMap = new HashMap<>();
        profileMap.put("first", profilText);
        profileMap.put("description", profileDescription);

        return FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION)
                .document(DOCUMENT_NAME)
                .collection(PROFILE_COLLECTION)
                .add(profileMap);
    }

    public static Task<Void> changeToFirebase(Profile profileToUpdate) {

        Map<String, Object> profilMap = new HashMap<>();

        profilMap.put("age",profileToUpdate.getAge());
        profilMap.put("bio",profileToUpdate.getBio());
        profilMap.put("birthday",profileToUpdate.getBirthday());
        profilMap.put("creationDate",profileToUpdate.getCreationDate());
        profilMap.put("currentAppVersion",profileToUpdate.getCurrentAppVersion());
        profilMap.put("email",profileToUpdate.getEmail());
        profilMap.put("employer",profileToUpdate.getEmployer());
        profilMap.put("fullName",profileToUpdate.getFullName());
        profilMap.put("instanceIDge",profileToUpdate.getInstanceID());
        profilMap.put("lastConnectionTime",profileToUpdate.getLastConnectionTime());
        profilMap.put("nickname",profileToUpdate.getNickname());
        profilMap.put("oS",profileToUpdate.getoS());
        profilMap.put("gender",profileToUpdate.getGender());
        profilMap.put("status",profileToUpdate.getStatus());
        profilMap.put("ville",profileToUpdate.getVille());
        profilMap.put("secretCode",profileToUpdate.getSecretCode());
        profilMap.put("difficulty",profileToUpdate.getDifficulty());

        return FirebaseFirestore.getInstance()
                .collection(CHAT_COLLECTION)
                .document(DOCUMENT_NAME)
                .collection(PROFILE_COLLECTION)
                .document(profileToUpdate.getIdentifier())
                .set(profilMap, SetOptions.merge());
    }

    public static void loadProfileCF(String userID) {

        DocumentReference profileReference = FirebaseFirestore.getInstance().collection("profiles").document(userID);

        profileReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {

            @Override
            // Override : On s'approprie la methode "onComplete" qui est étendue de "AppCompatActivity"
            // onComplete : Called when the Task completes.
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {

                        DocumentSnapshot document = task.getResult();

                        Log.d(TAG, "test before if loadProfilCF");

                        if (document.exists()) {

                            Log.d(TAG, "test if loadProfilCF");

                            String identifier = document.getId();

                            String bio = document.getString("bio");
                            Date birthday = document.getDate("birthday");
                            Date creationDate = document.getDate("creationDate");

                            String currentAppVersion = document.getString("currentAppVersion");
                            String email = document.getString("email");
                            String employer = document.getString("employer");
                            String fullName = document.getString("fullName");
                            String instanceID = document.getString("instanceID");
                            Date lastConnectionTime = document.getDate("lastConnectionTime");
                            String nickname = document.getString("nickname");
                            String oS = document.getString("oS");
                            Gender gender = Gender.valueFor(document.getString("gender"));
                            ProfileStatus status = ProfileStatus.valueFor(document.getString("status"));
                            String ville = document.getString("ville");

                            // PB avec les INT
                            int secretCode = (int) document.get("secretCode");
                            int difficulty = (int) document.get("difficulty");

                            // int secretCode = Integer.parseInt(Objects.requireNonNull(document.getString("secretCode")));
                            // int difficulty = Integer.parseInt(Objects.requireNonNull(document.getString("difficulty")));

                            // A remettre au début de la liste comme l'ordre de l'objet
                            Integer age = Integer.valueOf("age");

                            Profile profile = new Profile(age, bio, birthday, creationDate, currentAppVersion,
                                    email, employer, fullName, instanceID, lastConnectionTime,
                                    nickname, oS, gender, status, ville, secretCode, difficulty);

                            listenerCF.onProfileLoadedFromCF(profile);
                        }

                        else {

                            Log.d(TAG, "test else loadProfilCF");
                            listenerCF.onProfileNotFoundInCF();
                            Log.d(TAG, "test else loadProfilCF after listener");

                        }
                    }
                }
        });
    }

    public static void writeProfile(Profile profileToWrite){

            Profile profile = profileToWrite;
            Map<String, Object> profileMap = profile.toMap();
            CollectionReference profilesReference = FirebaseFirestore.getInstance().collection(PROFILE_COLLECTION);
            profilesReference.add(profileMap);


    }
}