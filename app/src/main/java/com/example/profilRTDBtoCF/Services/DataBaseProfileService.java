package com.example.profilRTDBtoCF.Services;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.profilRTDBtoCF.Gender;
import com.example.profilRTDBtoCF.Helper.Helper;
import com.example.profilRTDBtoCF.Listener.DataBaseServiceListener;
import com.example.profilRTDBtoCF.Manager.ProfileManager;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;

import static com.facebook.login.widget.ProfilePictureView.TAG;

public class DataBaseProfileService {

    private static final String CHAT_COLLECTION = "chats";
    private static final String DOCUMENT_NAME = "document";
    private static final String NOTE_COLLECTION = "notes";

    public static String userId = "10213328234656981";

    public static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child(userId);

    private static DataBaseServiceListener listenerRTDB;

    public static void setListenerRTDB(DataBaseServiceListener listenerRTDB) {
        DataBaseProfileService.listenerRTDB = listenerRTDB;
    }

    public static void readCFwriteRTDB() {

        Log.d(TAG, "test DataBaseProfileService readCFwriteRTDB");

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

                    // Parcourir tous les documents et donner les resultats (getResult)
                    for (QueryDocumentSnapshot document : task.getResult()) {

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

                        Profile profile = new Profile(identifier, age, bio, birthday, creationDate, currentAppVersion,
                                email, employer, fullName, instanceID, lastConnectionTime,
                                nickname, oS, gender, status, ville);


                        // ProfileManager.get_instance().getProfile().add(profile);

                        //----------
                        // WRITE RTDB
                        //----------
                        databaseReference.child(identifier).setValue(profile.toMap());

                        /*
                        Map<String, Object> noteHashMap = profile.toMap();

                        DatabaseReference noteRef = databaseReference.child(uid);

                        noteRef.setValue(noteHashMap);
                        */

                        listenerRTDB.onProfileLoadedFromRTDB(profile);
                    }
                }

                // On appelle le singleton en utilisant le "get instance"
                // ProfileManager.get_instance().profileLoaded();

            }
        });
    }

    //------------------------------------------------
    // Date To String et inversement + méthode liées
    //------------------------------------------------

    private static TimeZone utcTimeZone = TimeZone.getTimeZone("UTC");

    public static String convertDateToString(Date date){
        simpleDateFormat.setTimeZone(utcTimeZone);
        String formattedCurrentDate = simpleDateFormat.format(date);
        return formattedCurrentDate;
    }

    public static Date convertStringToDate(String dateAsString) throws ParseException {
        Date date;
        simpleDateFormat.setTimeZone(utcTimeZone);
        date = simpleDateFormat.parse(dateAsString);

        return date;
    }

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss +0000");

    //------------------
    // ReadProfileRTDB
    //------------------

    public static void readProfileRTDB() {
        Log.d(TAG, "test start readProfileRTDB");

        ValueEventListener profileListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot profileSnap) {
                Log.d(TAG, "test onDataChange readProfileRTDB");

                if (profileSnap.exists())
                {
                    Log.d(TAG, "test if onDataChange readProfileRTDB");

                    String identifier = ProfileManager.getUserId();

                    Integer age = profileSnap.child("age").getValue(Integer.class);
                    String bio = profileSnap.child("bio").getValue(String.class);

                    // Birthday
                    String birthdayString = profileSnap.child("birthday").getValue(String.class);
                    Date birthday = null;
                    try {
                        birthday = convertStringToDate(birthdayString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    // CréationDate
                    String creationDateString = profileSnap.child("creationDate").getValue(String.class);
                    Date creationDate = null;
                    try {
                        creationDate = convertStringToDate(creationDateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    String currentVersion = profileSnap.child("currentVersion").getValue(String.class);
                    String email = profileSnap.child("email").getValue(String.class);
                    String employer = profileSnap.child("employer").getValue(String.class);
                    String fullName = profileSnap.child("fullName").getValue(String.class);
                    String instanceID = profileSnap.child("instanceID").getValue(String.class);

                    // lastConnection
                    String lastConnectionString = profileSnap.child("lastConnection").child("date").getValue(String.class);
                    Date lastConnection = null;
                    try {
                        lastConnection = convertStringToDate(lastConnectionString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }


                    String nickname = profileSnap.child("nickname").getValue(String.class);
                    String oS = profileSnap.child("oS").getValue(String.class);
                    Gender gender = profileSnap.child("Gender").getValue(Gender.class);

                    // Status
                    String statusString = profileSnap.child("status").getValue(String.class);
                    ProfileStatus status = ProfileStatus.valueFor(statusString);

                    String ville = profileSnap.child("ville").getValue(String.class);

                    Profile profile = new Profile(identifier, age, bio, birthday, creationDate, currentVersion,
                            email, employer, fullName, instanceID, lastConnection,
                            nickname, oS, gender, status, ville);

                    listenerRTDB.onProfileLoadedFromRTDB(profile);
                }
                else { }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.d(TAG, "test loadPost:onCancelled", databaseError.toException());
                // ...
            }
        };

        // ajout du listener (profileListener) sur notre référence (databaseReference) avec "addValueEventListener"
        databaseReference.addValueEventListener(profileListener);
            }
    }