package com.example.profilRTDBtoCF.Services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.profilRTDBtoCF.Gender;
import com.example.profilRTDBtoCF.Helper.Helper;
import com.example.profilRTDBtoCF.Listener.DataBaseServiceListener;
import com.example.profilRTDBtoCF.Profile;
import com.example.profilRTDBtoCF.ProfileStatus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class DataBaseProfileService {

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";

    public static  DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    private static DataBaseServiceListener listenerRTDB;

    public static void setListenerRTDB(DataBaseServiceListener listenerRTDB) {
        DataBaseProfileService.listenerRTDB = listenerRTDB;
    }

    public static void readCFwriteRTDB() {

        // FirestoreProfileService.loadProfileCF();

        //----------
        // READ CF
        //----------

        CollectionReference notesReference = Helper.getNotes();

        notesReference.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            // Override : On s'approprie la methode "onComplete" qui est étendue de "AppCompatActivity"
            // onComplete : Called when the Task completes.
            public void onComplete(@NonNull Task<QuerySnapshot> task) {

                if (task.isSuccessful()) {

                    ArrayList<Profile> listProfile = new ArrayList<>();

                    // Parcourir tous les documents et donner les resultats (getResult)
                    for (QueryDocumentSnapshot document : task.getResult())
                    {

                         String identifier = document.getId();

                         Integer age = Integer.valueOf(document.getString("age"));
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
                         int secretCode = Integer.parseInt(Objects.requireNonNull(document.getString("secretCode")));
                         int difficulty = Integer.parseInt(Objects.requireNonNull(document.getString("difficulty")));

                        Profile profile = new Profile(age,bio,birthday,creationDate,currentAppVersion,
                                email,employer,fullName,instanceID,lastConnectionTime,
                                nickname,oS,gender,status,ville,secretCode,difficulty);

                        listProfile.add(profile);

                        // ProfileManager.get_instance().getProfiles().add(profile);

                        //----------
                        // WRITE RTDB
                        //----------
                        databaseReference.child(identifier).setValue(profile.toMap());

                        /*
                        Map<String, Object> noteHashMap = profile.toMap();

                        DatabaseReference noteRef = databaseReference.child(uid);

                        noteRef.setValue(noteHashMap);
                        */
                    }
                    listenerRTDB.onProfileLoadedFromRTDB(listProfile);
                    }

                    // On appelle le singleton en utilisant le "get instance"
                    // ProfileManager.get_instance().listLoaded();

                }
        });
    }

    public static void readProfileRTDB() {

        ValueEventListener noteListListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists())
                {
                    ArrayList<Profile> listProfile = new ArrayList<>();

                    // parcourir les snapshot des notes
                    for(DataSnapshot noteSnap : dataSnapshot.getChildren()) {

                        String identifier = noteSnap.getKey();
                        Integer age = noteSnap.child("age").getValue(Integer.class);
                        String bio = noteSnap.child("bio").getValue(String.class);

                        Date birthday = noteSnap.child("birthday").getValue(Date.class);
                        Date creationDate = noteSnap.child("creationDate").getValue(Date.class);
                        String currentAppVersion = noteSnap.child("currentAppVersion").getValue(String.class);
                        String email = noteSnap.child("email").getValue(String.class);
                        String employer = noteSnap.child("employer").getValue(String.class);
                        String fullName = noteSnap.child("fullName").getValue(String.class);
                        String instanceID = noteSnap.child("instanceID").getValue(String.class);
                        Date lastConnectionTime = noteSnap.child("lastConnectionTime").getValue(Date.class);
                        String nickname = noteSnap.child("nickname").getValue(String.class);
                        String oS = noteSnap.child("oS").getValue(String.class);
                        Gender gender = noteSnap.child("Gender").getValue(Gender.class);
                        ProfileStatus status = noteSnap.child("status").getValue(ProfileStatus.class);
                        String ville = noteSnap.child("ville").getValue(String.class);
                        int secretCode = noteSnap.child("secretCode").getValue(Integer.class);
                        int difficulty = noteSnap.child("difficulty").getValue(Integer.class);

                        Profile profile = new Profile(age,bio,birthday,creationDate,currentAppVersion,
                                email,employer,fullName,instanceID,lastConnectionTime,
                                nickname,oS,gender,status,ville,secretCode,difficulty);

                        listProfile.add(profile);
                    }

                    listenerRTDB.onProfileLoadedFromRTDB(listProfile);

                }
                else
                {

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        databaseReference.addValueEventListener(noteListListener);
    }


}